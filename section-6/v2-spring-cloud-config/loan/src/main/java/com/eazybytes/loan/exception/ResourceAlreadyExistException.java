package com.eazybytes.loan.exception;

public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldValue)
    {
        super(String.format("%S not found with given mobileNumber %s : '%s", resourceName, fieldName, fieldValue));
    }
}
