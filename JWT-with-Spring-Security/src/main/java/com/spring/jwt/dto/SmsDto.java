package com.spring.jwt.dto;

import lombok.Data;

@Data
public class SmsDto {
    private Integer smsId;
    private String mobileNo;
    private String otp;
    private String status;
}
