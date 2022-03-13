package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class WalletSecurityQuestion {

    private Long id;
        private Wallet walletId; // Add ManyToOne relationship because many wallet questions can be attributed to one wallet
    private int securityQuestionId;
    private String answer;
    private String answerHash;
    private String answerSalt;
}
