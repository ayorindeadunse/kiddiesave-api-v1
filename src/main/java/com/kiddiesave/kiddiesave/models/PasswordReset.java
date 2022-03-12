package com.kiddiesave.kiddiesave.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PasswordReset {
    private String email;
    private String token;
    private String salt;
    private Date dateCreated;
}
