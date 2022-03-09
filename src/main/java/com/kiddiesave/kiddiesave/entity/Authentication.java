package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Authentication {
    private String token;
    private String refreshToken;
}
