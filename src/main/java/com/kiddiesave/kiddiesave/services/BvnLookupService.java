package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public interface BvnLookupService {
    BvnLookupServiceResponse bvnLookup(String bvn) throws UnirestException, IOException;
}
