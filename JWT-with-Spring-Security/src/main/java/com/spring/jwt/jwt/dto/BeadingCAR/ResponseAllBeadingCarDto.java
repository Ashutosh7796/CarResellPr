package com.spring.jwt.jwt.dto.BeadingCAR;

import com.spring.jwt.dto.BeadingCAR.BeadingCARDto;

import java.util.List;

public class ResponseAllBeadingCarDto {
    private String message;
    private List<BeadingCARDto> list;
    private String exception;

    public ResponseAllBeadingCarDto(String message){
        this.message=message;
    }
}


