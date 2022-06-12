package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnValidationResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateOTPResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public interface ValidationServiceRest {
    ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws IOException, UnirestException;
    ValidateOTPResponse validateOTPCode(String pinId, String code) throws IOException, UnirestException;
    // validate bvn api 1
    BvnValidationResponse bvnLookup(String bvn) throws IOException, UnirestException;
}
