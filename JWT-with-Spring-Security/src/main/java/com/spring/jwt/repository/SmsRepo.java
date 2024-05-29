package com.spring.jwt.repository;


import com.spring.jwt.entity.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsRepo extends JpaRepository<SmsEntity,Integer> {

    SmsEntity findByMobileNoAndOtp(String mobileNo, String otp);

    List<SmsEntity> findByMobileNo (String mobileNo);

    @Query("SELECT s FROM SmsEntity s WHERE s.mobileNo = :mobileNo")
    SmsEntity findUserWithMobileNo(@Param("mobileNo") String mobileNo);

}
