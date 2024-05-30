package com.spring.jwt.repository;



import com.spring.jwt.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EmailVerificationRepo extends JpaRepository<EmailVerification,Integer> {
    EmailVerification findByEmail(String email);

    List<EmailVerification> findByCreationTimeBefore(LocalDateTime dateTime);

   /* @Modifying
    @Transactional
    @Query("DELETE FROM EmailVerification WHERE (otp IS NOT NULL AND userOTP IS NOT NULL AND otp != userOTP) OR userOTP IS NULL OR userOTP = ''")
    void deleteUser();*/

    void deleteByStatusAndCreationTimeBefore(String notVerified, LocalDateTime expirationTime);
}