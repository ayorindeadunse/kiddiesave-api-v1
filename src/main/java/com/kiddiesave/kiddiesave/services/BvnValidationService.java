package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnValidationResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public interface BvnValidationService {
    BvnValidationResponse verifyBvn(String bvn) throws UnirestException, IOException;
}
