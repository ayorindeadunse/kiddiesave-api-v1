package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.models.EmailModel;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{
    JavaMailSender mailSender;
    private Environment env;

    public EmailServiceImpl(JavaMailSender mailSender, Environment env) {
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    public void sendEmail(EmailModel emailModel) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject(emailModel.getSubject());
        helper.setFrom(env.getProperty("sprng.mail.username"));
        helper.setTo(emailModel.getTo());
        helper.setText(emailModel.getMessage());
        mailSender.send(helper.getMimeMessage());
    }

    @Override
    public void sendEmailWithAttachment(EmailModel emailModel) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setSubject(emailModel.getSubject());
        helper.setFrom(emailModel.getFrom());
        helper.setTo(emailModel.getTo());
        helper.setText(emailModel.getMessage());
        FileSystemResource file = new FileSystemResource(new File(emailModel.getAttachment()));
        helper.addAttachment("Attachment",file);
        mailSender.send(helper.getMimeMessage());
        System.out.println("Email Sent Successfully"); //replace with logger
    }
}
