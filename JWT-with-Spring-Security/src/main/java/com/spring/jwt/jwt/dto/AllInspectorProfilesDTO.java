package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.InspectorProfileDto;
import lombok.Data;

import java.util.List;

@Data
public class AllInspectorProfilesDTO {

    private String message;
    private Integer totalPages;
    private List<InspectorProfileDto> list;
    private String exception;

    public AllInspectorProfilesDTO(String message) {
        this.message = message;
    }
}

