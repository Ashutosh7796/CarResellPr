package com.spring.jwt.jwt.dto;


import lombok.Data;

@Data
public class PasswordChange {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}

