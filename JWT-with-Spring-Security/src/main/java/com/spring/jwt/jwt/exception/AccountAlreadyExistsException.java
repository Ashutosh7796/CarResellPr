package com.spring.jwt.jwt.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AccountAlreadyExistsException extends RuntimeException{
    private String code;

    private String message;

    public AccountAlreadyExistsException(String s) {
    }
}