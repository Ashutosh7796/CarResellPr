package com.spring.jwt.jwt.exception;

public class NewAndOldPasswordDoseNotMatchException extends RuntimeException{
    public NewAndOldPasswordDoseNotMatchException(String message) {
        super(message);
    }
}
