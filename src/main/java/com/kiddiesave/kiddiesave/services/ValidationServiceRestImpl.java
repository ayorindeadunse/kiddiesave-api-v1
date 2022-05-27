package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumber;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;


// test implementation
@Service
public class ValidationServiceRestImpl implements ValidationServiceRest{

    @Value("${SmsOtpUrl}")
    private String smsOtpUrl;
    @Value("${SmsOtpApiKey}")
    private String smsOtpApiKey;
    @Value("${SmsOtpSenderId}")
    private String senderId;
    @Value("${SmsOtpChannel}")
    private String smsOtpChannel;
    @Value("${SmsOtpExpiryTime}")
    private String smsOtpExpiryTime;

            public String sendOTPCode(String phoneNumber) throws UnirestException {

          /*  ValidatePhoneNumber validatePhoneNumber = new ValidatePhoneNumber();
            validatePhoneNumber.setApiKey(smsOtpApiKey);
            validatePhoneNumber.setMessageType("NUMERIC");
            validatePhoneNumber.setTo(phoneNumber);
            validatePhoneNumber.setFrom(senderId);
            validatePhoneNumber.setChannel(smsOtpChannel);
            validatePhoneNumber.setPinAttempts(3);
            validatePhoneNumber.setPinTimeToLive(5);
            validatePhoneNumber.setPinLength(6);
            validatePhoneNumber.setPinPlaceholder("<1234>");
            validatePhoneNumber.setMessageText("Welcome to Kiddiesave! Use this OTP <1234> to complete your registration. OTP expires in "+smsOtpExpiryTime);
            validatePhoneNumber.setPinType("Numeric");*/


                Unirest.setTimeouts(0, 0);
                HttpResponse<String> response = Unirest.post(smsOtpUrl)
                        .header("Content-Type", "application/json")
                        .body("{\r\n  \"api_key\" : \""+smsOtpApiKey+"\",\r\n \"message_type\" : \"NUMERIC\",\r\n  \"to\" : \""+phoneNumber+"\",\r\n       \"from\" : \""+senderId+"\",\r\n       \"channel\" : \""+smsOtpChannel+"\",\r\n       \"pin_attempts\" : 3,\r\n       \"pin_time_to_live\" :  5,\r\n       \"pin_length\" : 6,\r\n       \"pin_placeholder\" : \"<1234>\",\r\n       \"message_text\" : \"Welcome to Kiddiesave! Use this OTP <1234> to complete your registration. OTP expires in "+smsOtpExpiryTime+" minutes."+"\",\r\n       \"pin_type\" : \"NUMERIC\"\r\n   }\r\n      ")
                        .asString();

        return response.getBody();
    }
}
