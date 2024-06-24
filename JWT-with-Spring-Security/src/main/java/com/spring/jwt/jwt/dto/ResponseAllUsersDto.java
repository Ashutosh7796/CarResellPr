package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.UserProfileDto;
import lombok.Data;

import java.util.List;

@Data
public class ResponseAllUsersDto {

    private String message;
    private List<UserProfileDto> list;
    private String exception;

    public ResponseAllUsersDto(String message){
        this.message=message;
    }

}
