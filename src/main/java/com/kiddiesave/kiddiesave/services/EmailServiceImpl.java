package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{
    JavaMailSender mailSender;
   // private Environment env;
   @Value("${spring.mail.username}")
   private String username;

   @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
       // this.env = env;
    }

    @Override
    //public void sendEmail(EmailModel emailModel) throws MessagingException {
            public void sendEmail(String recipientEmail,String validationUrl) throws MessagingException {

       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setText(validationUrl);
        simpleMailMessage.setSubject("Email Validation");
        mailSender.send(simpleMailMessage);
        // Put recipient’s address

    }

    @Override
   // public void sendEmailWithAttachment(EmailModel emailModel) throws MessagingException {
            public void sendEmailWithAttachment(String recipientEmail,String attachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
       // helper.setSubject(emailModel.getSubject());
        helper.setSubject("Email Validation");
       // helper.setFrom(emailModel.getFrom());
        helper.setFrom(username);
       // helper.setTo(emailModel.getTo());
        helper.setTo(recipientEmail);
       // helper.setText(emailModel.getMessage());
        helper.setText("Test Message");
       // FileSystemResource file = new FileSystemResource(new File(emailModel.getAttachment()));
        FileSystemResource file = new FileSystemResource((new File(attachment)));
        helper.addAttachment("Attachment",file);
        mailSender.send(helper.getMimeMessage());
        System.out.println("Email Sent Successfully"); //replace with logger
    }
}
