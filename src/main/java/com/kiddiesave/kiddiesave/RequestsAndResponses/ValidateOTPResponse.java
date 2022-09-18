package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ValidateOTPResponse
{
    @NotNull(message="Pin Id cannot be null")
    @NotBlank(message="Pin Id cannot be blank")
    private String pinId;
    private String verified;
    private String msisdn;// phone number
}
