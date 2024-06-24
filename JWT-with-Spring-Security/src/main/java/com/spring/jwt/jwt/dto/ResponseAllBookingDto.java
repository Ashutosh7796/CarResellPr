package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.BookingDto;
import lombok.Data;

import java.util.List;

@Data
public class ResponseAllBookingDto {

    private String status;

    private String message;

    private List<BookingDto> bookings;

    private String exception;

    public ResponseAllBookingDto(String status) {
        this.status = status;
    }
}
