package com.kiddiesave.kiddiesave.RequestsAndResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BvnResponseResultObject {
    private String bvn;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String dob;
}
