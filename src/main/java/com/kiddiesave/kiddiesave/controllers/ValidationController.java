package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.*;
import com.kiddiesave.kiddiesave.services.BvnLookupServiceImpl;
import com.kiddiesave.kiddiesave.services.PhoneValidationServiceImpl;
import com.kiddiesave.kiddiesave.services.ValidateEmailServiceImpl;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.IOException;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@Validated
@RequestMapping("/api/validation")
public class ValidationController {

    private final PhoneValidationServiceImpl phoneValidationService;
    private final BvnLookupServiceImpl bvnLookupService;
    private ValidateEmailServiceImpl validateEmailService;

    @Autowired
    public ValidationController(PhoneValidationServiceImpl phoneValidationService, BvnLookupServiceImpl bvnLookupService,
                                ValidateEmailServiceImpl validateEmailService) {
        this.phoneValidationService = phoneValidationService;
        this.bvnLookupService = bvnLookupService;
        this.validateEmailService = validateEmailService;
    }

    //Allow anonymous
    @PostMapping("/otp/phone")
    public ResponseEntity<?> sendOtpCode(@Valid @RequestBody ValidatePhoneNumberRequest validatePhoneNumberRequest)
            throws UnirestException {

            // Mobile number validation already handled by regex in model
            String pin = phoneValidationService.generatePhoneValidationCode(validatePhoneNumberRequest);
            phoneValidationService.savePhoneNumber(validatePhoneNumberRequest.getPhone());

            if(pin != null) {
                return ResponseEntity.ok(new ApiResponse(true, "Otp sent successfully!", pin));
            }
            return null;
    }

    @GetMapping("/validateemail/{email}/{requestid}")
    public ResponseEntity<?> validateUserEmail(@PathVariable  @Email(message="Please pass a valid e-mail address") String email, @PathVariable String requestid)
    {
        //validate e-mail and requestId
        if(email == null || requestid == null)
        {
            return new ResponseEntity(new ApiResponse(false, "Email and request ID are required", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }
        String response = validateEmailService.validateUserEmail(email,requestid);
        if(response.equalsIgnoreCase("User email successfully validated. Please login to the app."))
            return ResponseEntity.ok(new ApiResponse(true, response,null));
        return null;
    }

    // Validate OTP Code ok
    @PostMapping("/phone/verify")
    public ResponseEntity<?> validatePhoneNumber(@Valid @RequestBody ValidateOTPRequest validateOTPRequest) throws UnirestException, IOException {
        if(validateOTPRequest.getCode() == null )
        {
            return new ResponseEntity(new ApiResponse(false, "OTP to be verified cannot be null or empty", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }
        // Return 404 here
        if(validateOTPRequest.getPinId() == null)
        {
            return new ResponseEntity(new ApiResponse(false, "PIN cannot be null or empty", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST); //create exception object to be logged in app insights that shows the message that pin is required and shouldn't be expired
        }
        ValidateOTPResponse validateOTPResponse = phoneValidationService.verifyOTP(validateOTPRequest.getPinId(), validateOTPRequest.getCode());
        if(validateOTPResponse.getVerified() == "false")
        {
            return new ResponseEntity(new ApiResponse(false, "OTP status cannot be determined", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST); //create exception object to be logged in app insights that shows the message that pin is required and shouldn't be expired
        }
        return ResponseEntity.ok(new ApiResponse(true, "Otp verified successfully!", validateOTPResponse));
    }

    //Verify BVN
    @PostMapping("/bvnlookup")
    public ResponseEntity<?> bvnLookUp(@Valid @RequestBody ValidateBvnRequest validateBvnRequest) throws IOException
    {
        if(validateBvnRequest == null)
        {
            return new ResponseEntity(new ApiResponse(false, "Bvn is required", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }
        BvnLookupServiceResponse bvnLookupServiceResponse =bvnLookupService.bvnLookup(validateBvnRequest.getBvn());
        return ResponseEntity.ok(new ApiResponse(true,"Bvn details returned successfully!",bvnLookupServiceResponse));
    }
}
