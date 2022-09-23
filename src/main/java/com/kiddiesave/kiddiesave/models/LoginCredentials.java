package com.kiddiesave.kiddiesave.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {
    private String email;
    private String password;
}
