package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.PendingBookingDTO;
import lombok.Data;

import java.util.List;

@Data

public class ResponseAllPendingBookingDto {
    private String message;
    private List<PendingBookingDTO> list;
    private String exception;

    public ResponseAllPendingBookingDto(String message){
        this.message=message;
    }

}
