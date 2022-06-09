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

@Getter
@Setter
@NoArgsConstructor
@ToString
class BvnData
{
    private String lastName;
    private String gender;
    private String firstName;
    private String dateOfBirth;
    private String middleName;
    private String bvn;

}
