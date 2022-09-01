package com.kiddiesave.kiddiesave.RequestsAndResponses;

import javax.validation.constraints.*;
import java.util.Set;

public class SignUpRequest {

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 40)
    @Email
    private String email;
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    @Pattern(regexp="\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$\"",message="length must be 8 characters, with at least  one uppercase letter, lower case letter and one special character")
    private String password;
    @NotBlank
    @NotNull
    @NotEmpty
    private String bvn;
    @NotBlank
    @NotNull
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotBlank
    @NotNull
    @NotEmpty
    private String lastName;
    @NotBlank
    @NotNull
    @NotEmpty
    private String address;
    @NotBlank
    @NotNull
    @NotEmpty
    private String gender;
    @NotBlank
    @NotNull
    @NotEmpty
    private String country;
    @NotBlank
    @NotNull
    @NotEmpty
    private Set<String> role;
    private String title;
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 13,message = "The minimum length of the mobile number should be 13 and start with 234.")
    @Pattern(regexp = "^234\\d{10}",message = "The minimum length of the mobile number should be 13 and start with 234.")
    private String mobile;
    @NotBlank
    @NotNull
    @NotEmpty
    private String dob;

    public SignUpRequest() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
