package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.kiddiesave.kiddiesave.entity.PhoneRecord;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface PhoneValidationService {
    String generatePhoneValidationCode(ValidatePhoneNumberRequest request) throws UnirestException;
    // save phone number
    PhoneRecord savePhoneNumber(String phone);
}
