package com.spring.jwt.jwt.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedException extends RuntimeException {


    private String code;

    private String message;

    public UnauthorizedException(String message) {
        super(message);
    }

}