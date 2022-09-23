package com.kiddiesave.kiddiesave.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
    @ElementCollection
    private List<String> imagesUr;
    private String subject;
    private String content;
    private boolean approvalStatus;
    private Date dateCreated;
    private Date dateUpdated;
    private String imagesHtml;

    public UserComplaint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }

    public List<String> getImagesUr() {
        return imagesUr;
    }

    public void setImagesUr(List<String> imagesUr) {
        this.imagesUr = imagesUr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getImagesHtml() {
        return imagesHtml;
    }

    public void setImagesHtml(String imagesHtml) {
        this.imagesHtml = imagesHtml;
    }
}
