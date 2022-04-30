package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String reference;
    private Double amount;
    private String drAccount;
    private String crAccount;
    private String narration;
    private Date dateCreated;
    private String source;
    private String counterPartyBank;
    private String counterPartyService;
    private String counterPartyPhone;
    private String counterPartyBankCode;
    private String counterPartyChannel;
    private String counterParty;
    private String cbaReference;
    private String trnCode;
    private String status;
    private String bankCode;
    private String bankName;
    private String drAccountName;
    private String crAccountName;
    private String responseMessage;
    private String nibssResponseMessage;
    private String sessionId;
    private String responseCode;
}
