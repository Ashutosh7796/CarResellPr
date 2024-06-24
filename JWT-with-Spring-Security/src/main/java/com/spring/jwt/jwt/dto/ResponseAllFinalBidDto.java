package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.FinalBidDto;
import lombok.Data;

import java.util.List;
@Data
public class ResponseAllFinalBidDto { private String message;
    private List<com.spring.jwt.dto.FinalBidDto> placedBids;
    private String exception;

    public ResponseAllFinalBidDto(String message, List<FinalBidDto> placedBids, String exception) {
        this.message = message;
        this.placedBids = placedBids;
        this.exception = exception;
    }
}
