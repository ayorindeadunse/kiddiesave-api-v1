package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import com.kiddiesave.kiddiesave.services.ValidationServiceRestImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/validation")
public class ValidationController {

    private final ValidationServiceRestImpl validationServiceRest;

    public ValidationController(ValidationServiceRestImpl validationServiceRest) {
        this.validationServiceRest = validationServiceRest;
    }

    @PostMapping("/generateotp")
    public ResponseEntity<?> ValidatePhoneNumber(@Valid @RequestBody ValidatePhoneNumberRequest validatePhoneNumberRequest) {

        return null;
    }
}