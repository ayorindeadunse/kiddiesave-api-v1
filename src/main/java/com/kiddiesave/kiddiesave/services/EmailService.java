package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.models.EmailModel;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String recipientEmail) throws MessagingException;
    void sendEmailWithAttachment(String recipientEmail,String attachment) throws MessagingException;
}
