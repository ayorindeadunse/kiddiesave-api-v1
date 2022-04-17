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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getPhoneNumberLinkedWithBvn() {
        return phoneNumberLinkedWithBvn;
    }

    public void setPhoneNumberLinkedWithBvn(String phoneNumberLinkedWithBvn) {
        this.phoneNumberLinkedWithBvn = phoneNumberLinkedWithBvn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }

    public int getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    public boolean isLockedOutEnabled() {
        return lockedOutEnabled;
    }

    public void setLockedOutEnabled(boolean lockedOutEnabled) {
        this.lockedOutEnabled = lockedOutEnabled;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public String getReferralPhoneNo() {
        return referralPhoneNo;
    }

    public void setReferralPhoneNo(String referralPhoneNo) {
        this.referralPhoneNo = referralPhoneNo;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isBvnValidated() {
        return isBvnValidated;
    }

    public void setBvnValidated(boolean bvnValidated) {
        isBvnValidated = bvnValidated;
    }

    public boolean isPhoneValidated() {
        return isPhoneValidated;
    }

    public void setPhoneValidated(boolean phoneValidated) {
        isPhoneValidated = phoneValidated;
    }

    public boolean isPushNotifications() {
        return isPushNotifications;
    }

    public void setPushNotifications(boolean pushNotifications) {
        isPushNotifications = pushNotifications;
    }

    public String getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(String notificationId) {
        NotificationId = notificationId;
    }

    public String getPinHash() {
        return pinHash;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    public String getPinSalt() {
        return pinSalt;
    }

    public void setPinSalt(String pinSalt) {
        this.pinSalt = pinSalt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
