package com.kiddiesave.kiddiesave.RequestsAndResponses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateUserRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nin;
    private String address;
    private String gender;
    private String country;
    private String title;
    private String mobile;

}
