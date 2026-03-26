package com.eazybytes.accounts.repository;


import com.eazybytes.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {


    Optional<Accounts> findByCustomerId(Long customerId);

    // ---------------------- Below i have made changes for      LEC 22 DELETE API inside accounts microservice

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId); // here customerId is not primary key in Account entity, so we explicitly have to create this method as Jpa will not provide any support for this
}
