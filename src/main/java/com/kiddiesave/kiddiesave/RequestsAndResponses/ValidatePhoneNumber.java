package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@Getter
@Setter
@JsonComponent
public class ValidatePhoneNumber {
    private String apiKey;
    private String messageType;
    private String to;
    private String from;
    private String channel;
    private int pinAttempts;
    private int pinTimeToLive;
    private int pinLength;
    private String pinPlaceholder;
    private String messageText;
    private String pinType;
}
