package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignUpResponse {
    private String email;
    private String bvn;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String gender;
    private String country;
    private Set<String> role;
    private String title;
    private String mobile;
    private String dob;
}
