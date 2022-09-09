package com.kiddiesave.kiddiesave.RequestsAndResponses;

import com.kiddiesave.kiddiesave.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class ClaimsResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Set<Role> roles;
    private int mobile;
    private String bvn;
    private String gender;
    private Date dob;
    private String title;
    private String country;
}
