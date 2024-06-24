package com.spring.jwt.jwt.exception;

public class UserNotDealerException extends RuntimeException{
    public UserNotDealerException(String message) {
        super(message);
    }
}
