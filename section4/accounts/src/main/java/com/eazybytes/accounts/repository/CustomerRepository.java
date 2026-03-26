package com.eazybytes.accounts.repository;


import com.eazybytes.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


     /* Here we are using Optional
     *
     * MobileNumber  is mobileNumber in Pojo entity  Customer  also it is not case-     * sensitive but spelling should be correct
     *  this  " findByMobileNumber "  is called derived named method because on the method name Spring Data Jpa framework will prepare the SQL query
     * and run the same on to the database
     *
     *   */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
