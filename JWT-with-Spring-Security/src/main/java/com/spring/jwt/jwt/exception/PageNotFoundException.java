package com.spring.jwt.jwt.exception;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String pageNotFound) {
            super(pageNotFound);
        }

}

