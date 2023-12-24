package com.ForAll.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
@AllArgsConstructor
public class MailSender {
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public MailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String email, String subject) {
        try{
            MimeMessage mailMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper  = new MimeMessageHelper(mailMessage, "utf-8");
            helper.setSubject(subject);
            helper.setFrom(emailFrom);
            String htmlMsg = "";
            helper.setText(htmlMsg, true);
            helper.setTo(email);
            emailSender.send(mailMessage);

        } catch (MailException | MessagingException e){
            //TODO: implements a log with error message (issue #4)

        }
    }


}
