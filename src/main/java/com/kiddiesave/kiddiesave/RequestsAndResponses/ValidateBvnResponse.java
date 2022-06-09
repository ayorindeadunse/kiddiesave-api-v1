package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValidateBvnResponse {
    private Date responseDateTime;
    private String lastName;
    private String responseCode;
    private String firstName;
    private String phoneNumber;
    private BvnData bvnData;
}

class BvnData
{
    private String lastName;
    private String gender;
    private String firstName;
    private String dateOfBirth;
    private String middleName;
    private String bvn;

    BvnData(String lastName, String gender, String firstName, String dateOfBirth, String middleName, String bvn) {
        this.lastName = lastName;
        this.gender = gender;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.middleName = middleName;
        this.bvn = bvn;
    }
}
