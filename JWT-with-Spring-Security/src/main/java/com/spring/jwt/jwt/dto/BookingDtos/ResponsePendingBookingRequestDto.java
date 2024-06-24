package com.spring.jwt.jwt.dto.BookingDtos;

import com.spring.jwt.dto.BookingDtos.PendingBookingRequestDto;
import lombok.Data;

@Data
public class ResponsePendingBookingRequestDto {
    private String message;
    private PendingBookingRequestDto pendingBookingRequestDto;
    private String exception;

    public ResponsePendingBookingRequestDto(String message) {
        this.message = message;
    }
}
