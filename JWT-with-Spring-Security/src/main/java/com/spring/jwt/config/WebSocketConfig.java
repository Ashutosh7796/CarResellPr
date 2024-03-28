package com.spring.jwt.config;

import com.spring.jwt.Interfaces.PlacedBidService;
import com.spring.jwt.dto.BeedingDtos.PlacedBidDTO;
import com.spring.jwt.entity.PlacedBid;
import com.spring.jwt.exception.BidAmountLessException;
import com.spring.jwt.exception.BidForSelfAuctionException;
import com.spring.jwt.repository.PlacedBidRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig {

    private final PlacedBidService placedBidService;
    private final SimpMessagingTemplate messagingTemplate;
    private final PlacedBidRepo placedBidRepo;

    @MessageMapping("/placeBid")
    public void placeBid(@Payload PlacedBidDTO placedBidDTO, @Header("simpSessionId") String sessionId) {
        try {
            String result = placedBidService.placeBid(placedBidDTO, placedBidDTO.getBidCarId());
            List<PlacedBidDTO> topThreeBids = placedBidService.getTopThreeBidsrealtime(placedBidDTO.getBidCarId());
            String message = "Top three bids for car ID " + placedBidDTO.getBidCarId() + " retrieved successfully";
            messagingTemplate.convertAndSendToUser(sessionId, "/topic/bids", message);


             PlacedBid placedBid = new PlacedBid();
             placedBid.setUserId(placedBidDTO.getUserId());
             placedBid.setBidCarId(placedBidDTO.getBidCarId());
             placedBid.setDateTime(LocalDateTime.now());
             placedBid.setAmount(placedBidDTO.getAmount());
            placedBidRepo.save(placedBid);
        } catch (BidAmountLessException e) {
            String errorMessage = "Bid amount is less than the current highest bid amount.";
            messagingTemplate.convertAndSendToUser(sessionId, "/topic/bids/error", errorMessage);
        } catch (BidForSelfAuctionException e) {
            String errorMessage = "You cannot bid for your own auction.";
            messagingTemplate.convertAndSendToUser(sessionId, "/topic/bids/error", errorMessage);
        } catch (Exception e) {
            String errorMessage = "An error occurred while placing the bid: " + e.getMessage();
            messagingTemplate.convertAndSendToUser(sessionId, "/topic/bids/error", errorMessage);
        }
    }
}
