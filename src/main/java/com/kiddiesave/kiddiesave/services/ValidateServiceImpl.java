package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.kiddiesave.kiddiesave.entity.PhoneRecord;

public class ValidateServiceImpl implements ValidationService{

    @Override
    public String generatePhoneValidationCode(ValidatePhoneNumberRequest request) {
        return null;
    }

    @Override
    public PhoneRecord savePhoneNumber(String phone) {
        return null;
    }
}
