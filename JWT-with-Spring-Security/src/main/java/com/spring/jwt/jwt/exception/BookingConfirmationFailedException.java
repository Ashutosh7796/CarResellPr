package com.spring.jwt.jwt.exception;

public class BookingConfirmationFailedException extends RuntimeException {

    private String message;

    public BookingConfirmationFailedException(String message) {
        this.message = message;
    }
}
