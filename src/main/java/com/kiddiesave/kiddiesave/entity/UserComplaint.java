package com.kiddiesave.kiddiesave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String reference;
    private String response;
    private String responder;

    //Add the entity relationship with an imagesUrl table
    private List<String> imagesUrl;
    private String subject;
    private String content;
    private boolean approvalStatus;
    private Date dateCreated;
    private Date dateUpdated;
    private String imagesHtml;

}
