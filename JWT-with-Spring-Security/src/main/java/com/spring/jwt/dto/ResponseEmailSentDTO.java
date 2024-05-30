package com.spring.jwt.dto;

import lombok.Data;

@Data
public class ResponseEmailSentDTO {
    String status;
    String message;

    public ResponseEmailSentDTO(String message) {
        this.message = message;
    }
}