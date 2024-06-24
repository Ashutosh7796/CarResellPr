package com.spring.jwt.jwt.dto.BookingDtos;

import com.spring.jwt.dto.BookingDtos.PendingBookingDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PendingBookingResponseForSingleDealerDto {
    private String message;
    private PendingBookingDTO pendingBookingDTO;
    private String exception;

    public PendingBookingResponseForSingleDealerDto(String message) {
        this.message = message;
    }
}
