package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.CreateWalletResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public interface WalletServiceRest {
    CreateWalletResponse createWallet(String email) throws UnirestException;
}
