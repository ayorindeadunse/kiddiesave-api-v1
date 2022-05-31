package com.kiddiesave.kiddiesave.RequestsAndResponses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValidateOTPRequest {

    private String pinId;
    private String code;
}