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
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    private User userId;

    private WalletType walletType;
    private boolean status;
    private String password;
    private String accountNumber;
    private String minimumBalance;
    private String accountName;
    private String accountBalance;
    private String balanceUncleared;
    private int accessFailedCount;
    private boolean isLockedOut;
    private Date lockedOutDate;
    private boolean lockedOutEnabled;
    private int tierType;
    private Date dateCreated;
    private Date dateUpdated;

}
