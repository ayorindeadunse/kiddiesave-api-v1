package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.*;
import com.kiddiesave.kiddiesave.services.PhoneValidationServiceImpl;
import com.kiddiesave.kiddiesave.services.ValidationServiceRestImpl;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/validation")
public class ValidationController {

    private final PhoneValidationServiceImpl phoneValidationService;

    public ValidationController(PhoneValidationServiceImpl phoneValidationService) {
        this.phoneValidationService = phoneValidationService;
    }

    //Allow anonymous
    @PostMapping("/phone")
    public ResponseEntity<?> ValidatePhoneNumber(@Valid @RequestBody ValidatePhoneNumberRequest validatePhoneNumberRequest)
            throws UnirestException {

            // Mobile number validation already handled by regex in model
            String pin = phoneValidationService.generatePhoneValidationCode(validatePhoneNumberRequest);
            phoneValidationService.savePhoneNumber(validatePhoneNumberRequest.getPhone());

            if(pin != null) {
                return ResponseEntity.ok(new ApiResponse(true, "Otp sent successfully!", pin));
            }
            else
            {
                return new ResponseEntity(new ApiResponse(false, "An Error occurred on the server!", HttpStatus.INTERNAL_SERVER_ERROR),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

    // Validate OTP Codeokl
    @PostMapping("/phone/verify")
    public ResponseEntity<?> ValidatePhoneNumber(@Valid @RequestBody ValidateOTPRequest validateOTPRequest) throws UnirestException, IOException {
        if(validateOTPRequest.getCode() == null)
        {
            return new ResponseEntity(new ApiResponse(false, "OTP to be verified cannot be null or empty", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }
        if(validateOTPRequest.getPinId() == null)
        {
            return new ResponseEntity(new ApiResponse(false, "OTP to be verified cannot be null or empty", HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR); //create exception object to be logged in app insights that shows the message that pin is required and shouldn't be expired
        }
        ValidateOTPResponse validateOTPResponse = phoneValidationService.verifyOTP(validateOTPRequest.getPinId(), validateOTPRequest.getCode());
        return ResponseEntity.ok(new ApiResponse(true, "Otp verified successfully!", validateOTPResponse));
    }
}
