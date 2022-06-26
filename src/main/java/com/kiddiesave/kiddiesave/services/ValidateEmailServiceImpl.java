package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateEmailRequest;
import com.kiddiesave.kiddiesave.entity.EmailValidationData;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.EmailValidationDataRepository;
import com.kiddiesave.kiddiesave.repository.UserRepository;

import java.util.Date;
import java.util.UUID;

public class ValidateEmailServiceImpl implements ValidateEmailService{

    private final EmailValidationDataRepository emailValidationDataRepository;
    private final UserRepository userRepository;

    public ValidateEmailServiceImpl(EmailValidationDataRepository emailValidationDataRepository, UserRepository userRepository) {
        this.emailValidationDataRepository = emailValidationDataRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void saveEmailValidationRequest(ValidateEmailRequest request) {
        EmailValidationData val = new EmailValidationData();
        val.setEmail(request.getEmail());
        val.setRequestId(request.getRequestId());
        val.setDateCreated(new Date());

        emailValidationDataRepository.save(val);

    }

    @Override
    public String sendValidationEmail(ValidateEmailRequest request) {
        try
        {
            // get user
            User u = userRepository.getUserByEmail(request.getEmail());
            if(u == null || !u.isStatus())
            {
                throw new UserNotFoundException("User does not exist");
            }
            UUID uuid = UUID.randomUUID();
            String requestId = uuid.toString();
            /**send validation email
             * *
             */
            // save data in database
            request.setRequestId(requestId);
            saveEmailValidationRequest(request);
            return "Email successfully sent to user";
        }
        // catch custom validation exception here
        catch(Exception e)
        {
            System.out.println("Failure to send validation e-mail "  + e);
        }
        return null;
    }
}