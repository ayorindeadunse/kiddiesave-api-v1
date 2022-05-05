package com.kiddiesave.kiddiesave.services;

import java.io.IOException;

public interface ValidationServiceRest {
//ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws IOException;
    String sendOTPCode(String phoneNumber) throws IOException;
}
