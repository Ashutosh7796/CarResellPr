package com.spring.jwt.jwt.exception;

public class EmailNotExistException extends RuntimeException{
    public EmailNotExistException(String message) {
        super(message);
    }
}
