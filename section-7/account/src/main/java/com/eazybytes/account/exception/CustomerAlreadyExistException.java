package com.eazybytes.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerAlreadyExistException extends RuntimeException{

    public CustomerAlreadyExistException(String message)
    {
        super(message);
    }
}
