package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import org.springframework.beans.factory.annotation.Value;

public class ValidationServiceRestImpl implements ValidationServiceRest{

    @Value("${SmsOtpUrl}")
    private String smsOtpUrl;
    @Value("${SmsOtpApiKey}")
    private String smsOtpApiKey; //host these details in GCP or AWS when it's deployed
    @Override
    public ValidatePhoneNumberResponse sentOTPCode(String phoneNumber) {
        ValidatePhoneNumberResponse responseData = null;
        try
        {

        }
        catch(Exception e)
        {
            System.out.println("Error: "+e);
        }
    }
}
