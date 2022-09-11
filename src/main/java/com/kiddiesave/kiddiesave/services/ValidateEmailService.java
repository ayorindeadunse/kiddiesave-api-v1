package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateEmailRequest;

import javax.mail.MessagingException;

public interface ValidateEmailService {
    // contracts to validate a user's email address upon registration
    void saveEmailValidationRequest(ValidateEmailRequest request);
    String sendValidationEmail(String userEmail) throws MessagingException;
}
