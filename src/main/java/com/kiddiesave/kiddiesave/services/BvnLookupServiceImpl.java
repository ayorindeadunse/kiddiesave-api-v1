package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BvnLookupServiceImpl implements BvnLookupService {
    @Autowired
    private final ValidationServiceRestImpl validationServiceRest;

    public BvnLookupServiceImpl(ValidationServiceRestImpl validationServiceRest) {
        this.validationServiceRest = validationServiceRest;
    }


    @Override
    public BvnLookupServiceResponse bvnLookup(String bvn) throws UnirestException, IOException {
        BvnLookupServiceResponse bvnValidationResponse = validationServiceRest.bvnLookup(bvn);
        return bvnValidationResponse;
    }
}
