package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.CreateWalletResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.Response;

import java.io.IOException;

public interface WalletServiceRest {
    //CreateWalletResponse createWallet(String email) throws IOException;
    Response createWallet(String email) throws IOException;
}
