package com.kiddiesave.kiddiesave.RequestsAndResponses;

import javax.validation.constraints.*;
import java.util.Set;

public class SignUpRequest {

    @NotNull(message="email address is required")
    @Size(max = 40)
    @Email(message="Please enter a valid e-mail address")
    private String email;
    @NotNull(message="password is required")
    @Size(min = 6, max = 20)
    @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message="length must be 8 characters, with at least  one uppercase letter, lower case letter, one number and one special character")
    private String password;
    @NotNull(message="bvn is required")
    private String bvn;
    @NotNull(message="First name is required")
    private String firstName;
    private String middleName;
    @NotNull(message="Last name is required")
    private String lastName;
    @NotNull(message="address is required")
    private String address;
    @NotNull(message="Gender is required")
    private String gender;
    @NotNull(message="Country is required")
    private String country;
    @NotNull(message="The role is required")
    private Set<String> role;
    private String title;
    @NotNull
    @Size(min = 13,message = "The minimum length of the mobile number should be 13 characters.")
    private String mobile;
    @NotNull(message="Date of Birth is required")
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
