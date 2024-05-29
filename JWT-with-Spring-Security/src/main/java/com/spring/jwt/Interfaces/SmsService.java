package com.spring.jwt.Interfaces;


import com.spring.jwt.dto.SmsDto;
import com.spring.jwt.entity.SmsEntity;

public interface SmsService {
    void sendSms(String message , String number , String apiKey);

    void saveOtp(SmsEntity smsEntity);

    boolean verifyOtp(SmsDto smsDto);

    public boolean canResendOtp(String mobileNo);

    public void removePreviousOtp(String mobileNo);

}
