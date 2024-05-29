package com.spring.jwt.repository;


import com.spring.jwt.entity.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepo extends JpaRepository<SmsEntity,Integer> {

    SmsEntity findByMobileNoAndOtp(Long mobileNo, String otp);

    SmsEntity findByMobileNo (String mobileNo);
}
