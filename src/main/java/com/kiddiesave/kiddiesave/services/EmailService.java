package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.models.EmailModel;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(EmailModel emailModel) throws MessagingException;
    void sendEmailWithAttachment(EmailModel emailModel) throws MessagingException;
}
