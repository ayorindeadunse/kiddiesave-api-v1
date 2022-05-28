package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PhoneRecord {
    private Long id;
    private String phone;
    private Date dateCreated;
}
