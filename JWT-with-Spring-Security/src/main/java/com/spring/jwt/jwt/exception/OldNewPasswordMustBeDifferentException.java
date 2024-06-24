package com.spring.jwt.jwt.exception;

public class OldNewPasswordMustBeDifferentException extends RuntimeException {

    public OldNewPasswordMustBeDifferentException(String message) {
        super(message);
    }
}
