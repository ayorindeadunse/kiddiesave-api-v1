package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ValidatePhoneNumberRequest {
    @NotBlank
    @Pattern(regexp = "^234\\d{10}",message = "The minimum length of the mobile number should be 13 and start with 234.")
    private String phone;
}
