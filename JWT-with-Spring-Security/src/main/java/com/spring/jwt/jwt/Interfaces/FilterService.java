package com.spring.jwt.jwt.Interfaces;

import com.spring.jwt.dto.CarDto;
import com.spring.jwt.dto.FilterDto;

import java.util.List;

public interface FilterService {
    public List<CarDto> searchByFilter(FilterDto filterDto);

    public List<CarDto> getAllCarsWithPages(int PageNo);
}
