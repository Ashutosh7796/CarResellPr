package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.BeadingCAR.BeadingCARDto;
import com.spring.jwt.dto.CarDto;
import com.spring.jwt.dto.FilterDto;
import com.spring.jwt.entity.Status;

import java.util.List;
import java.util.UUID;

public interface BeadingCarService {

    public String AddBCar(BeadingCARDto beadingCARDto);
    public String editCarDetails(BeadingCARDto beadingCARDto, UUID beadingCarId);
    public List<BeadingCARDto> getAllBeadingCars();
    public String deleteBCar(UUID beadingCarId);

   public BeadingCARDto getBCarById(UUID beadingCarId);

   public List<BeadingCARDto>getByUserId(int UserId);





}
