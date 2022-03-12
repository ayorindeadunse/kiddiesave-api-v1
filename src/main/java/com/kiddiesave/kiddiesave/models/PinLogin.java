package com.kiddiesave.kiddiesave.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PinLogin {
    private String pin;
    private String confirmPin;
}
