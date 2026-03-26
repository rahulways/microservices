package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import org.springframework.data.repository.query.Param;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

 // ---------------------- BELOW I HAVE MADE CHANGES AS     LEC 20 READ API inside accounts microservice
    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Account details based on given mobileNumber
     */
    CustomerDto  fetchAccount(String mobileNumber);


// ---------------------- Below i have made changes as      LEC 21 UPDATE API inside accounts microservice

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);  // This method will tell weather update operation has been successfully or not

// ---------------------- Below i have made changes for      LEC 22 DELETE API inside accounts microservice

  boolean deleteAccount(String mobileNumber);









}
