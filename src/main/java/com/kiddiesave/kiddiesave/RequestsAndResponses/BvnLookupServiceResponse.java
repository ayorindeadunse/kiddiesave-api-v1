package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString

public class BvnLookupServiceResponse {

        private String title;
        private String full_name;
        private String firstName;
        private String middleName;
        private String lastName;
        private String email;
        private String gender;
        private String dob;
        private String phone;
        private String alternatePhone;
        private String avatar;
        private String country;
        private String stateOfOrigin;
        private String stateOfResidence;
        private String lgaOfOrigin;
        private String lgaOfResidence;
        private String addressLineOne;
        private String addressLineTwo;
        private String addressLineThree;
        private String maritalStatus;
        private String nin;
        private String nationality;
        private String registrationDate;
        private String enrollmentBank;
        private String enrollmentBranch;
        private String accountLevel;
        private Boolean watchlisted;
}
