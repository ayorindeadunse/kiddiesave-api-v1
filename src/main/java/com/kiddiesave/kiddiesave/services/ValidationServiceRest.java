package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;

public interface ValidationServiceRest {
ValidatePhoneNumberResponse sendOTPCode(String phoneNumber);
}
