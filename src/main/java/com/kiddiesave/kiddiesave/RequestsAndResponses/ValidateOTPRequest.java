package com.kiddiesave.kiddiesave.RequestsAndResponses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValidateOTPRequest {
    @NotNull(message="Pin ID cannot be null")
    private String pinId;
    @NotNull(message="Code cannot be null")
    private String code;
}