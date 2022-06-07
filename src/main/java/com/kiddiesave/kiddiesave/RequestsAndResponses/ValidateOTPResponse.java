package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateOTPResponse
{
    private String pinId;
    private String verified;
    private String msisdn;// phone number
}
