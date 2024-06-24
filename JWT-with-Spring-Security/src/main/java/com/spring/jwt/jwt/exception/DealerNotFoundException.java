package com.spring.jwt.jwt.exception;

public class DealerNotFoundException extends RuntimeException{
    public DealerNotFoundException(String message) {
        super(message);
    }
}
