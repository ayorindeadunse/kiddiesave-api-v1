package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    @Pattern(regexp="\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$\"",message="length must be 8 characters, with at least  one uppercase letter, lower case letter and one special character")
    private String password;
    @NotBlank
    private String bvn;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @NotBlank
    private String gender;
    private Set<String> role;
    private String title;
    @NotBlank
    private String mobile;
    @NotBlank
    private Date dob;

}
