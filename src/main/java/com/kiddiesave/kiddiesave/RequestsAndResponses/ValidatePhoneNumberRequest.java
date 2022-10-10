package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ValidatePhoneNumberRequest {
    @NotNull(message="Phone cannot be null")
    @Size(min = 13,message = "The minimum length of the mobile number should be 13 characters.")
    private String phone;
}
