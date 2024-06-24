package com.spring.jwt.jwt.exception;

public class PendingBookingNotFoundException extends RuntimeException{

    private String Message;
    private String bookingMessage;

    public PendingBookingNotFoundException (String Message, String bookingMessage) {
        this.Message = Message;
        this.bookingMessage = bookingMessage;
    }

}
