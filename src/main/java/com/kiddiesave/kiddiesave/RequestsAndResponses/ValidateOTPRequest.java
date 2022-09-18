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
    @NotBlank(message="Pin Id cannot be blank")
    private String pinId;
    @NotNull(message="Code cannot be null")
    @NotBlank(message="Code cannot be blank")
    private String code;
}