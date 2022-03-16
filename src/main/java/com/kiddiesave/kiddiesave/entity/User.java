package com.kiddiesave.kiddiesave.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ToString
@NoArgsConstructor
public class User {
    @Id // Marks the Id field as primary key and therefore the identifier of this entity.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    //update with the rest of user properties.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String bvn;
    private String title;
    private String nin;
    private String phoneNumberLinkedWithBvn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String gender;
    private UserType userType;
    private String notified;
    private int accessFailedCount;
    private boolean lockedOutEnabled; // use the methods provided for in UserDetails class for this implementation
    private String address;
    private String referralName;
    private String referralPhoneNo;
    private String dateCreated;
    private String dateUpdated;
    private String deviceId;
    private String refreshToken;
    private boolean isBvnValidated;
    private String isPhoneValidated;
    private boolean isPushNotifications; //push notifications enabled or not
    private String NotificationId;
    private String pinHash;
    private String pinSalt;
    private String status;


}
