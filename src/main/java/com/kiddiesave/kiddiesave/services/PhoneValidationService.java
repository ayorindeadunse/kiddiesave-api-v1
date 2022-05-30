package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface PhoneValidationService {
    String generatePhoneValidationCode(ValidatePhoneNumberRequest request) throws UnirestException;
    void savePhoneNumber(String phone);
}
