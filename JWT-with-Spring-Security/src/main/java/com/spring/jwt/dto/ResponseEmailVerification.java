package com.spring.jwt.dto;


import lombok.Data;

@Data
public class ResponseEmailVerification {
    String status;
    String message;

    public ResponseEmailVerification(String message) {
        this.message = message;
    }

    public ResponseEmailVerification() {
    }
}