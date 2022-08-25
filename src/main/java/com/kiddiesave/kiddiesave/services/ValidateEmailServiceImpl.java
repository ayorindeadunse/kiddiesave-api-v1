package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateEmailRequest;
import com.kiddiesave.kiddiesave.entity.EmailValidationData;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.EmailValidationDataRepository;
import com.kiddiesave.kiddiesave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ValidateEmailServiceImpl implements ValidateEmailService{

    private final EmailValidationDataRepository emailValidationDataRepository;
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @Autowired
    public ValidateEmailServiceImpl(EmailServiceImpl emailService,EmailValidationDataRepository emailValidationDataRepository, UserRepository userRepository) {
        this.emailValidationDataRepository = emailValidationDataRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    @Override
    public void saveEmailValidationRequest(ValidateEmailRequest request) {
        EmailValidationData val = new EmailValidationData();
        val.setEmail(request.getEmail());
        val.setRequestId(request.getRequestId());
        val.setDateCreated(new Date());

        emailValidationDataRepository.save(val);

    }

    // method to send the actual e-mail
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
           // emailService.sendEmail(); // try implementation 2022 -07 - 27 //add sendgrid implementation afterwards
            // save data in database
            request.setRequestId(requestId);
            //has request id before saving in database(consider)
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
