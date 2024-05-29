package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.SmsService;
import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.RegisterDto;
import com.spring.jwt.dto.SmsDto;
import com.spring.jwt.entity.SmsEntity;
import com.spring.jwt.exception.BaseException;
import com.spring.jwt.exception.MobileNumberNotVerifiedException;
import com.spring.jwt.exception.UserAlreadyExistException;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private SmsService smsService;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO> register(@RequestBody RegisterDto registerDto) {
        try {
            BaseResponseDTO response = userService.registerAccount(registerDto);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", response.getMessage()));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "User already exists"));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "Invalid role"));
        } catch (MobileNumberNotVerifiedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody SmsDto smsDto) {
        logger.info("Program Started....");

        String mobileNo = smsDto.getMobileNo();
        if (!smsService.canResendOtp(mobileNo)) {
            return "Please wait 3 minutes before requesting a new OTP.";
        }

        // Remove the previous OTP before sending a new one
        smsService.removePreviousOtp(mobileNo);

        String otp = OtpUtil.generateOtp(6);
        logger.info("Generated OTP: {}", otp);

        String apiKey = "QYX1V75W9cI3fhegGSonlzrktEBOjKZb4CuvpxPdiN68JUFLDM6EBkZzrWM3FdHTaLo8epOJ7vRQDqnV";
        String number = String.valueOf(smsDto.getMobileNo());

        smsService.sendSms(otp, number, apiKey);

        SmsEntity smsEntity = new SmsEntity();
        smsEntity.setMobileNo(mobileNo);
        smsEntity.setOtp(otp);
        smsEntity.setStatus("Pending");
        smsService.saveOtp(smsEntity);

        return "OTP sent successfully";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestBody SmsDto smsDto) {
        boolean isValid = smsService.verifyOtp(smsDto);
        return isValid ? "OTP Verified Successfully" : "Invalid OTP. Please enter the valid OTP.";
    }
}
