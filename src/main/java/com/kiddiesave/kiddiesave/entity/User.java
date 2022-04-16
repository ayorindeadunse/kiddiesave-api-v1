package com.kiddiesave.kiddiesave.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phoneNumberLinkedWithBvn"),
        @UniqueConstraint(columnNames = "bvn")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

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
    private String country;
    private UserType userType;
    private String notified;
    private int accessFailedCount;
   private boolean lockedOutEnabled;
    private String address;
    private String referralName;
    private String referralPhoneNo;
    private Date dateCreated;
    private Date dateUpdated;
    private String deviceId;
    private String refreshToken;
    private boolean isBvnValidated;
    private boolean isPhoneValidated;
    private boolean isPushNotifications;
    private String NotificationId;
    private String pinHash;
    private String pinSalt;
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password, String address, String firstName, String middleName,
                String lastName, String gender, String bvn, String title, String country, String phoneNumberLinkedWithBvn,
                Date dob) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.bvn = bvn;
        this.title = title;
        this.phoneNumberLinkedWithBvn = phoneNumberLinkedWithBvn;
        this.dob = dob;
        this.country = country;
    }

}
