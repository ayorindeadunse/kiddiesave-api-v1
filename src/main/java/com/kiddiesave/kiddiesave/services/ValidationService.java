package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;

public interface ValidationService {
    String generatePhoneValidationCode(ValidatePhoneNumberRequest request);
}
