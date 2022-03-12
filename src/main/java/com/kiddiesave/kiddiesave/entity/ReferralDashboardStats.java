package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReferralDashboardStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long totalReferred;
    private Long completedReferral;
    private Double totalEarned;
    private Double pendingEarnings;

}
