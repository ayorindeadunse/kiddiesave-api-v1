package com.kiddiesave.kiddiesave.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmailModel {
    private String from;
    private String  to;
    private String subject;
    private String message;
    private String mailCC;
    private String mailBcc;
    private String contentType;
    private String attachment;

}
