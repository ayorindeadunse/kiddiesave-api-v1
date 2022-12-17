package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.CreateWalletResponse;
import com.kiddiesave.kiddiesave.security.util.LocalDateAdapter;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class WalletServiceRestImpl implements WalletServiceRest{

    @Value("${get_wallets_secret_key}")
    private String getWalletsSecretKey;
    @Value("${get_wallets_base_url}")
    private String getWalletsBaseUrl;

    private final LocalDateAdapter localDateAdapter;

    @Autowired
    public WalletServiceRestImpl(LocalDateAdapter localDateAdapter) {
        this.localDateAdapter = localDateAdapter;
    }
    @Override
    public CreateWalletResponse createWallet(String email) throws UnirestException {
    return null;
    }
}
