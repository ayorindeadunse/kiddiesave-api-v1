package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ApiResponse;
import com.kiddiesave.kiddiesave.services.WalletServiceImpl;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Email;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/api/wallets")
public class WalletsController {
    private final WalletServiceImpl walletService;

    @Autowired
    public WalletsController(WalletServiceImpl walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/createwallet")
    public ResponseEntity<?> createWallet(@PathVariable  @Email(message="Please pass a valid e-mail address") String email)
            throws IOException {
        if(email == null)
        {
            return new ResponseEntity(new ApiResponse(false, "Email id required", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }
        Response response = walletService.createWallet(email);
            return ResponseEntity.ok(response);
    }
}
