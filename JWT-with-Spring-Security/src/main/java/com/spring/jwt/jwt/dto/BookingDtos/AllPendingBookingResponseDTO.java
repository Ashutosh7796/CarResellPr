package com.spring.jwt.jwt.dto.BookingDtos;

import com.spring.jwt.dto.BookingDtos.PendingBookingDTO;
import lombok.Data;

import java.util.List;

@Data

public class AllPendingBookingResponseDTO {
    private String message;
    private List<PendingBookingDTO> list;
    private String exception;

    public AllPendingBookingResponseDTO(String message){
        this.message=message;
    }

}
