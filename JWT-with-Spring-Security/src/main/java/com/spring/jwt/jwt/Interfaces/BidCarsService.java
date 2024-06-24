package com.spring.jwt.jwt.Interfaces;

import com.spring.jwt.dto.BidCarsDTO;
import com.spring.jwt.dto.BidDetailsDTO;

import java.util.List;

public interface BidCarsService {

    public BidCarsDTO createBidding(BidCarsDTO bidCarsDTO);

    public BidDetailsDTO getbyBidId (Integer bidCarId,  Integer beadingCarId);


    public List<BidCarsDTO> getByUserId(Integer userId);
}
