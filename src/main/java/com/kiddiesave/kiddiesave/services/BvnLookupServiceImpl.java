package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BvnLookupServiceImpl implements BvnLookupService {

    private final ValidationServiceRestImpl validationServiceRest;

    @Autowired
    public BvnLookupServiceImpl(ValidationServiceRestImpl validationServiceRest) {
        this.validationServiceRest = validationServiceRest;
    }

    @Override
    public BvnLookupServiceResponse bvnLookup(String bvn) throws IOException {
        BvnLookupServiceResponse bvnLookupServiceResponse = validationServiceRest.bvnLookup(bvn);
        return bvnLookupServiceResponse;
    }
}
