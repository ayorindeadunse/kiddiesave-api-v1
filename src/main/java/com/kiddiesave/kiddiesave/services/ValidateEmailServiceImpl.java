package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateEmailRequest;
import com.kiddiesave.kiddiesave.entity.EmailValidationData;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.ApplicationException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.EmailValidationDataRepository;
import com.kiddiesave.kiddiesave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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
    public String sendValidationEmail(String userEmail) throws MessagingException {
        // use custom exception handler
       // try
       // {
            // get user
            User u = userRepository.getUserByEmail(userEmail);
            if(u == null)
            {
                throw new UserNotFoundException("User does not exist");
            }
            else if(u.isStatus() == true)
            {
                throw new ApplicationException("User e-mail has already been validated");
            }
            UUID uuid = UUID.randomUUID();
            String requestId = uuid.toString();
            //Generate validate email url
            String validationUrl = "http://localhost:8080/api/validation/"+userEmail+"/"+requestId;

            /**send validation email
             * * As  the email error is giving too many connections. Simulate storing the
             * user email in the database with the reference number and calling the endpoint to activate
             * the user account.
             */

          //  emailService.sendEmail(userEmail,validationUrl); // try implementation 2022 -07 - 27 //add sendgrid implementation afterwards
            // save data in database
          //  request.setRequestId(requestId);
            //has request id before saving in database(consider)
            ValidateEmailRequest validateEmailRequest = new ValidateEmailRequest();
            validateEmailRequest.setEmail(userEmail);
            validateEmailRequest.setRequestId(requestId);
            saveEmailValidationRequest(validateEmailRequest);
            return "Email successfully sent to user";
       // }
        // catch custom validation exception here
      //  catch(Exception e)
     //   {
      //      System.out.println("Failure to send validation e-mail "  + e);
    //    }
     //   return null;
    }

    @Override
    public String validateUserEmail(String userEmail, String requestId) {
        // check if user validation data exists in email_validation_data
        // if email exists, query user table and update user status
        // send success to user otherwise throw bad request
        EmailValidationData emailValidationData =
                emailValidationDataRepository.findEmailValidationDataByEmailAndRequestId(userEmail,requestId);

        if(emailValidationData != null)
        {
            User user = userRepository.getUserByEmail(userEmail);
            if(user != null)
            {
                //update user status
                user.setEmailValidated(true);
                userRepository.save(user);
                //  delete record from email_validation_data table
                emailValidationDataRepository.deleteEmailValidationDataByEmail(userEmail);
            }
            return "User email successfully validated. Please login to the app.";
        }
        throw new ApplicationException("User e-mail validation token does not exist or is invalid. Kindly " +
                "request for new validation email");

    }
}
