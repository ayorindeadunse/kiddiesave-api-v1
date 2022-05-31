package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateOTPResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public interface PhoneValidationService {
    String generatePhoneValidationCode(ValidatePhoneNumberRequest request) throws UnirestException;
    void savePhoneNumber(String phone);
    ValidateOTPResponse verifyOTP(String pinId, String code) throws UnirestException, IOException;
}
