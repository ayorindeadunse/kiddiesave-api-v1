package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public interface BvnLookupService {
  //  BvnLookupServiceResponse bvnLookup(String bvn) throws UnirestException, IOException;
    BvnLookupServiceResponse response(String bvn) throws UnirestException,IOException;
}
