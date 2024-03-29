package com.kiddiesave.kiddiesave.services;

import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WalletServiceImpl implements WalletService{

    private final WalletServiceRestImpl walletServiceRest;

    @Autowired
    public WalletServiceImpl(WalletServiceRestImpl walletServiceRest) {
        this.walletServiceRest = walletServiceRest;
    }

    @Override
    public Response createWallet(String email) throws IOException {
        Response createUserWallet = walletServiceRest.createWallet(email);
        return createUserWallet;
    }
}
