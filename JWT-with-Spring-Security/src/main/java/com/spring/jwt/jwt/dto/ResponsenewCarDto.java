package com.spring.jwt.jwt.dto;

import com.spring.jwt.dto.CarDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class ResponsenewCarDto {

        private String status;
        private List<CarDto> cars;
        private int totalPages;

        public ResponsenewCarDto(String status, List<CarDto> cars, int totalPages) {
            this.status = status;
            this.cars = cars;
            this.totalPages = totalPages;
        }
}
