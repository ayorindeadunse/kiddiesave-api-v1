package com.kiddiesave.kiddiesave.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
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
    private UserType userType;
    private String notified;
    private int accessFailedCount;
   private boolean lockedOutEnabled;
    private String address;
    private String referralName;
    private String referralPhoneNo;
    private String dateCreated;
    private String dateUpdated;
    private String deviceId;
    private String refreshToken;
    private boolean isBvnValidated;
    private String isPhoneValidated;
    private boolean isPushNotifications;
    private String NotificationId;
    private String pinHash;
    private String pinSalt;
    private String status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
