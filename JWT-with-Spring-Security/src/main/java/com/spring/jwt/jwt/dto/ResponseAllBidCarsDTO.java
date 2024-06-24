package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.BidCarsDTO;
import lombok.Data;

import java.util.List;
@Data
public class ResponseAllBidCarsDTO {

    private String status;

    private String message;

    private List<BidCarsDTO> bookings;

    private String exception;

    public ResponseAllBidCarsDTO(String status) {
        this.status = status;
    }
}

