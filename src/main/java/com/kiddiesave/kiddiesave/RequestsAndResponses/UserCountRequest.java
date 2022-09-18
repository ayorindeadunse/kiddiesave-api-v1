package com.kiddiesave.kiddiesave.RequestsAndResponses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserCountRequest {

    // TODO: Add validation to ensure just boolean fields are allowed
    private Boolean userStatus;
}
