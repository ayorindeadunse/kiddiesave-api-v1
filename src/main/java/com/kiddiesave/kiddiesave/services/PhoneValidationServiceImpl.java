package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateOTPResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import com.kiddiesave.kiddiesave.entity.PhoneRecord;
import com.kiddiesave.kiddiesave.repository.PhoneNumberRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class PhoneValidationServiceImpl implements PhoneValidationService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final ValidationServiceRestImpl validationServiceRest;

    public PhoneValidationServiceImpl(PhoneNumberRepository phoneNumberRepository,ValidationServiceRestImpl validationServiceRest) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.validationServiceRest = validationServiceRest;
    }

    @Override
    public String generatePhoneValidationCode(ValidatePhoneNumberRequest request) throws UnirestException {
        ValidatePhoneNumberResponse validatePhoneNumberResponse = validationServiceRest.sendOTPCode(request.getPhone());
        return validatePhoneNumberResponse.getPinId();
    }

    @Override
    public void savePhoneNumber(String phone) {
        PhoneRecord pr = new PhoneRecord();
        pr.setPhone(phone);
        pr.setDateCreated(new Date());

        phoneNumberRepository.save(pr);
    }

    @Override
    public ValidateOTPResponse verifyOTP(String pinId, String code) throws UnirestException, IOException {
        ValidateOTPResponse validateOTPResponse = validationServiceRest.validateOTPCode(pinId,code);
        return validateOTPResponse;
    }
}
