package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.CarVerifyDto;
import lombok.Data;

import java.util.List;
@Data
public class ResponseCarVerifyDto {

        private String message;
        private List<CarVerifyDto> list;
        private String exception;

        public ResponseCarVerifyDto(String message){
            this.message=message;
        }
    }


