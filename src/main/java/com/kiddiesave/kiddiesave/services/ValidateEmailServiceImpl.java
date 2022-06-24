package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateEmailRequest;
import com.kiddiesave.kiddiesave.entity.EmailValidationData;
import com.kiddiesave.kiddiesave.repository.EmailValidationDataRepository;

import java.util.Date;

public class ValidateEmailServiceImpl implements ValidateEmailService{

    private final EmailValidationDataRepository emailValidationDataRepository;

    public ValidateEmailServiceImpl(EmailValidationDataRepository emailValidationDataRepository) {
        this.emailValidationDataRepository = emailValidationDataRepository;
    }


    @Override
    public void saveEmailValidationRequest(ValidateEmailRequest request) {
        EmailValidationData val = new EmailValidationData();
        val.setEmail(request.getEmail());
        val.setRequestId(request.getRequestId());
        val.setDateCreated(new Date());

        emailValidationDataRepository.save(val);

    }
}
