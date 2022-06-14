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
        private String first_name;
        private String middle_name;
        private String last_name;
        private String email;
        private String gender;
        private String dob;
        private String phone;
        private String alternate_phone;
        private String avatar;
        private String country;
        private String state_of_origin;
        private String state_of_residence;
        private String lga_of_origin;
        private String lga_of_residence;
        private String address_line_1;
        private String address_line_2;
        private String address_line_3;
        private String marital_status;
        private String nin;
        private String nationality;
        private String registration_date;
        private String enrollment_bank;
        private String enrollment_branch;
        private String account_level;
        private Boolean watchlisted;
}
