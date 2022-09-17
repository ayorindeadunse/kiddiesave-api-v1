package com.kiddiesave.kiddiesave.RequestsAndResponses;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotBlank(message="email address(username) cannot be blank")
    @NotNull(message="email address(username) is required")
    @Email(message="email address(username) is invalid")
    private String usernameOrEmail;
    @NotBlank(message="password cannot be blank")
    @NotNull(message="password is required")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

}
