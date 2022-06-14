package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.Response;
import okhttp3.ResponseBody;
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


    /*public Response bvnLookup(String bvn) throws UnirestException, IOException {
      //  BvnLookupServiceResponse bvnValidationResponse = validationServiceRest.bvnLookup(bvn);
        Response response = validationServiceRest.bvnLookup(bvn);
        return response;
    }*/

    @Override
    public BvnLookupServiceResponse response(String bvn) throws IOException {
        //  BvnLookupServiceResponse bvnValidationResponse = validationServiceRest.bvnLookup(bvn);
        BvnLookupServiceResponse response = validationServiceRest.bvnLookup(bvn);
        return response;
    }
}
