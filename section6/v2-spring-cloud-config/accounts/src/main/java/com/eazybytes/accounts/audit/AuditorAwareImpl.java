package com.eazybytes.accounts.audit;


import org.springframework.stereotype.Component;
import  org.springframework.data.domain.AuditorAware;
import java.util.Optional;

@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional <String> getCurrentAuditor() {


        return Optional.of("ACCOUNTS_MS");// <----------------------   i have made changes for      LEC 25 Update audit columns using Spring Data


        //   return Optional.of("SYSTEM");

    }
}