package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValidateEmailRequest {
    @Email(message="Email is invalid")
    @NotNull(message="Email cannot be null")
    public String email;
    @NotNull(message="Request Id cannot be null")
    public String requestId;
    public Date dateCreated;
}
