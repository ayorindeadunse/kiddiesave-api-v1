package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //referral user id mapped to user table
    private String accountName;
    private String accountNumber;
    private Double amount;
    private String status;
    private Long streakDaysCount;
    private Date referralStartDate;
    private Date referralEndDate;
    private Date dateCreated;
    private Date dateModified;

}
