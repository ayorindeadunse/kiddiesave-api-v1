package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatePhoneNumberResponse
{
    private String pinId;
    private String smsStatus;
    private int status;
    private int attemptsRemaining;
    private boolean verified;
}
