package com.kiddiesave.kiddiesave.services;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String recipientEmail,String validationUrl) throws MessagingException;
    void sendEmailWithAttachment(String recipientEmail,String attachment) throws MessagingException;
}
