package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumber;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import org.springframework.beans.factory.annotation.Value;

public class ValidationServiceRestImpl implements ValidationServiceRest{

    @Value("${SmsOtpUrl}")
    private String smsOtpUrl;
    @Value("${SmsOtpApiKey}")
    private String smsOtpApiKey;
    @Value("${SmsOtpSenderId}")
    private String senderId;
    @Value("${SmsOtpChannel}")
    private String smsOtpChannel;
    @Value("${SmsOtpExpirtyTime}")
    private String smsOtpExpiryTime;
    @Override
    public ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) {
        ValidatePhoneNumberResponse responseData = null;
        try
        {
            ValidatePhoneNumber validatePhoneNumber = new ValidatePhoneNumber();
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
            validatePhoneNumber.setPinType("Numeric");

            // send request


        }
        catch(Exception e)
        {
            System.out.println("Error: "+e);
        }
    }
}
