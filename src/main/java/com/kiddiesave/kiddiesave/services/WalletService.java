package com.kiddiesave.kiddiesave.services;

import okhttp3.Response;

import java.io.IOException;

public interface WalletService {
    Response createWallet(String email) throws IOException;
}
