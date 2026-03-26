package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    // no need to use @Autowire on top of these interface because their constructor is single argument also we are using @AllArgsConstructor
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;





    @Override
    public void createAccount(CustomerDto customerDto) {
        // with below line all the data that i have inside costumer Dto will be transferred to new Customer()   object and will be stored in customer
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()) { // isPresent() returns true if there is any record present

            throw new CustomerAlreadyExistsException(" Customer already registered with given mobileNumber " + customerDto.getMobileNumber());

        }

        //     customer.setCreatedAt(LocalDateTime.now());
     //   customer.setCreatedBy("Rahul has created this customer ");

        // when below will get saved for the first time then customer id will be generated for the first time , so we will store it in  savedCustomer
        Customer savedCustomer = customerRepository.save(customer);  // here by default costumer id will get saved to savedCustomer  when we call save() for first time

        accountsRepository.save(createNewAccount(savedCustomer));
    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */

    // Below is a simple method that takes Customer entity class and will return a account entity class
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        //   newAccount.setCreatedAt(LocalDateTime.now());
     //   newAccount.setCreatedBy("Rahul has created this account ");
        return newAccount;
    }

    // ---------------------- BELOW I HAVE MADE CHANGES AS     LEC 20 READ API inside accounts microservice
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        /* here findByMobileNumber(mobileNumber)  is going to return an optional of customer , inside optional we have the method orElseThrow()
                using which we can throw an exception if there is no record present inside the optional
        */

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)

        );


        // ---------------------- BELOW I HAVE MADE CHANGES AS     LEC 20 READ API inside accounts microservice

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())

        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

// ---------------------- Below i have made changes as      LEC 21 UPDATE API inside accounts microservice


    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);  // here we are populating all the data from accountsDto  to  accounts  " here accounts is Entity Object "   using     " mapToAccounts  method"
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);  // This method will transfer all the data from  customerDto to customer which is entity object
            customerRepository.save(customer); // save
            isUpdated = true;
        }
        return  isUpdated;
    }

    // ---------------------- Below i have made changes for      LEC 22 DELETE API inside accounts microservice

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());    // here customerId is not primary key in Account entity, so we explicitly have to create this method as Jpa will not provide any support for this
        customerRepository.deleteById(customer.getCustomerId());   //  here deleteById  is being provided by Jpa itself
        return true;
    }

}
