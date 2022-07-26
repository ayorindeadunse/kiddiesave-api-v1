package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.models.EmailModel;

public interface EmailService {
    void sendEmail(EmailModel emailModel);
    void sendEmailWithAttachment(EmailModel emailModel);
}
