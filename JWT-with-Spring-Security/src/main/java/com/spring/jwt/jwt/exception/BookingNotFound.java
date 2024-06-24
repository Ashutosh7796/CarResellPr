package com.spring.jwt.jwt.exception;

import org.springframework.http.HttpStatus;

public class BookingNotFound extends RuntimeException {
    public BookingNotFound(String bookingNotFound, HttpStatus notFound) {
    }
}
