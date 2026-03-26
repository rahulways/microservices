package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String resourceName,String fieldName, String fieldValue) {


      /*  super(message);      as we know that here super(message); RuntimeException constructor accepts only single value as "message" so
                            " String resourceName,String fieldName, String fieldValue " we have to convert into single value  */


        super(String.format("%s not found with the given input data %s:'%s'",resourceName,fieldName,fieldValue));
    }
}