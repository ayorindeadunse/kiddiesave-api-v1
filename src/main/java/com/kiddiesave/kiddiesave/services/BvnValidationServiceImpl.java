package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnValidationResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BvnValidationServiceImpl implements BvnValidationService{
    @Autowired
    private final ValidationServiceRestImpl validationServiceRest;

    public BvnValidationServiceImpl(ValidationServiceRestImpl validationServiceRest) {
        this.validationServiceRest = validationServiceRest;
    }


    @Override
    public BvnValidationResponse verifyBvn(String bvn) throws UnirestException, IOException {
        BvnValidationResponse bvnValidationResponse = validationServiceRest.bvnLookup(bvn);
        return bvnValidationResponse;
    }
}
