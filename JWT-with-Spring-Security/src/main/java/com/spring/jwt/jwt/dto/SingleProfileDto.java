package com.spring.jwt.jwt.dto;
import com.spring.jwt.dto.InspectorProfileDto;
import lombok.Data;

@Data
public class SingleProfileDto {

    private String status;
    private InspectorProfileDto Response;

    public SingleProfileDto(String status) {
        this.status = status;
    }

}
