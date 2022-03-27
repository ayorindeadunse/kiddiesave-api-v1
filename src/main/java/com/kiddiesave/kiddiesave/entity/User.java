package com.kiddiesave.kiddiesave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class User implements UserDetails {
    // Possibly refactor this User class to implement/extend UserDetails so you can make use of Spring Security
    // features
    @Id // Marks the Id field as primary key and therefore the identifier of this entity.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    //update with the rest of user properties.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;
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
   // private boolean lockedOutEnabled; // use the methods provided for in UserDetails class for this implementation
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


    public User(String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static User build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
    @JsonIgnore
    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getUsername()
    {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
}
