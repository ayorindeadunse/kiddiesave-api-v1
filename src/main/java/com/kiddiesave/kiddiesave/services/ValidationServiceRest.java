package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import java.io.IOException;

public interface ValidationServiceRest {
//ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws IOException;
    StringBuilder sendOTPCode(String phoneNumber) throws IOException;
}
