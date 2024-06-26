package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.BeadingCAR.BeadingCARDto;

import java.util.List;
import java.util.UUID;

public interface BeadingCarService {

    public String AddBCar(BeadingCARDto beadingCARDto);
    public String editCarDetails(BeadingCARDto beadingCARDto, Integer beadingCarId);
    public List<BeadingCARDto> getAllBeadingCars();
    public String deleteBCar(Integer beadingCarId);

   public BeadingCARDto getBCarById(Integer beadingCarId);

   public List<BeadingCARDto>getByUserId(int UserId);





}
