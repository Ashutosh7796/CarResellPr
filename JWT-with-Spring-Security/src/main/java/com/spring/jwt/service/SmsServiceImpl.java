package com.spring.jwt.service;

import com.spring.jwt.Interfaces.SmsService;
import com.spring.jwt.dto.SmsDto;
import com.spring.jwt.entity.SmsEntity;
import com.spring.jwt.repository.SmsRepo;
import com.spring.jwt.utils.OtpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service // Indicates that this class is a Spring service component
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class); // Logger for logging information
    private static final Map<Long, String> otpCache = new HashMap<>(); // Cache to store OTPs

    @Autowired // Automatically injects an instance of SmsRepo
    private SmsRepo smsRepo;

    @Override
    public void sendSms(String message, String number, String apiKey) {
        try {
            String sendId = "FSTSMS"; // Sender ID for the SMS
            String language = "english"; // Language of the message
            String route = "p"; // Route for the SMS

            message = URLEncoder.encode(message, StandardCharsets.UTF_8); // Encode the message to UTF-8

            // Construct the URL for the SMS API request
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey
                    + "&sender_id=" + sendId + "&message=" + message + "&language=" + language
                    + "&route=" + route + "&numbers=" + number;

            URL url = new URL(myUrl); // Create a URL object
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection(); // Open a connection to the URL

            con.setRequestMethod("GET"); // Set the request method to GET
            con.setRequestProperty("User-Agent", "Mozilla/5.0"); // Set the User-Agent property
            con.setRequestProperty("cache-control", "no-cache"); // Set the cache-control property

            int responseCode = con.getResponseCode(); // Get the response code from the server
            logger.info("Response Code: {}", responseCode); // Log the response code

            StringBuffer response = new StringBuffer(); // Buffer to store the response
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); // Reader to read the response

            String line;
            while ((line = br.readLine()) != null) { // Read the response line by line
                response.append(line); // Append each line to the response buffer
            }
            br.close(); // Close the BufferedReader

            logger.info("Response from SMS API: {}", response.toString()); // Log the response from the API

        } catch (Exception e) {
            logger.error("Error while sending SMS: ", e); // Log any errors that occur
        }
    }

    @Override
    public void saveOtp(SmsEntity smsEntity) {
        try {
            String salt = OtpUtil.generateSalt(); // Generate a salt for the OTP
            String hashedOtp = OtpUtil.hashOtp(smsEntity.getOtp(), salt); // Hash the OTP with the salt
            smsEntity.setOtp(hashedOtp); // Set the hashed OTP in the entity
            smsEntity.setSalt(salt); // Set the salt in the entity
            smsEntity.setCreatedAt(LocalDateTime.now()); // Set the current time as the creation time
            smsRepo.save(smsEntity); // Save the entity to the repository
            otpCache.put(smsEntity.getMobileNo(), hashedOtp); // Store the hashed OTP in the cache
        } catch (Exception e) {
            logger.error("Error while saving OTP: ", e); // Log any errors that occur
        }
    }

    @Override
    public boolean verifyOtp(SmsDto smsDto) {
        String cachedOtp = otpCache.get(smsDto.getMobileNo()); // Retrieve the cached OTP for the mobile number
        SmsEntity smsEntity = smsRepo.findByMobileNoAndOtp(smsDto.getMobileNo(), cachedOtp); // Find the entity with the mobile number and OTP
        if (smsEntity != null) { // If the entity is found
            try {
                String inputHashedOtp = OtpUtil.hashOtp(smsDto.getOtp(), smsEntity.getSalt()); // Hash the input OTP with the salt
                if (cachedOtp.equals(inputHashedOtp)) { // Check if the hashed OTP matches the cached OTP
                    LocalDateTime now = LocalDateTime.now(); // Get the current time
                    LocalDateTime createdAt = smsEntity.getCreatedAt(); // Get the creation time of the OTP
                    if (ChronoUnit.MINUTES.between(createdAt, now) <= 3) { // Check if the OTP is within the validity period (3 minutes)
                        smsEntity.setStatus("Verified"); // Update the status to "Verified"
                        smsRepo.save(smsEntity); // Save the updated entity to the repository
                        otpCache.remove(smsDto.getMobileNo()); // Remove the OTP from the cache
                        return true; // OTP is valid
                    } else {
                        smsRepo.delete(smsEntity); // Delete the entity if the OTP is expired
                        otpCache.remove(smsDto.getMobileNo()); // Remove the OTP from the cache
                    }
                }
            } catch (Exception e) {
                logger.error("Error while verifying OTP: ", e); // Log any errors that occur
            }
        }
        return false; // OTP is invalid
    }

}
