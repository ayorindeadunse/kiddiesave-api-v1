package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.kiddiesave.kiddiesave.entity.PhoneRecord;

public interface PhoneValidationService {
    String generatePhoneValidationCode(ValidatePhoneNumberRequest request);
    // save phone number
    PhoneRecord savePhoneNumber(String phone);
}
