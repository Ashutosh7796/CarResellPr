package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SmsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer smsId;

    @Column(name = "mobileNo")
    private String mobileNo;

    @Column(name = "otp")
    private String otp;

    @Column(name = "salt")
    private String salt;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status;


    public SmsEntity() {
        this.createdAt = LocalDateTime.now();
    }
}
