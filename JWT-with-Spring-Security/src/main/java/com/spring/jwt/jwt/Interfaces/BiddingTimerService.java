package com.spring.jwt.jwt.Interfaces;

import com.spring.jwt.dto.BiddingTimerRequestDTO;

import java.util.List;


public interface BiddingTimerService {

    public BiddingTimerRequestDTO startTimer (BiddingTimerRequestDTO biddingTimerRequest);

//    void sendNotification (String recipient, String message);

    void sendBulkEmails(List<String> recipients, String message);

}
