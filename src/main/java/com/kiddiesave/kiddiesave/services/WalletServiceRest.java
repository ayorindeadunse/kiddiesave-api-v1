package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.CreateWalletResponse;

import java.io.IOException;

public interface WalletServiceRest {
    CreateWalletResponse createWallet(String email) throws IOException;
}
