package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatePhoneNumber {
    private String apiKey;
    private String messageType;
    private String to;
    private String from;
    private String channel;
    private String pinAttempts;
    private int pinTimeToLive;
    private int pinLength;
    private String pinPlaceholder;
    private String messageText;
    private String pinType;
}
