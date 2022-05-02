package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class ValidatePhoneNumberRequest {
    @NotBlank
    private String phone;
    private String code;
    private String pinId;
    private Date dateCreated;
}
