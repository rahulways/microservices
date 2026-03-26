package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{


    public CustomerAlreadyExistsException(String message ){
        super(message); /* here using super what actually we are doing is that whenever someone is trying to create object of CustomerAlreadyExistsException by
                         passing this message value then the same we are passing this to runtime exception because we are extending this to super class
                         RuntimeException   hence we are also invoking parent constructor as well */

    }
}
