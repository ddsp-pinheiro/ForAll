package com.ForAll.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MailSender {

    @Value("${spring.mail.username}")
    private String emailFrom;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.smtp.host}")
    private String host;
    @Value("${spring.mail.smtp.port}")
    private String port;
    @Value("${spring.mail.smtp.auth}")
    private String auth;
    @Value("${spring.mail.smtp.starttls.enable}")
    private String starttls;

    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable",starttls);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port",port);

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });
    }

    public void sendMail(String email,String subject,String pathTemplate) {
       try {
           Message message = new MimeMessage(getSession());
           message.setFrom(new InternetAddress(emailFrom));
           message.setRecipients(
                   Message.RecipientType.TO, InternetAddress.parse(email));
           message.setSubject(subject);
           String msg = Jsoup.parse(new File(pathTemplate), "UTF-8").toString();

           MimeBodyPart mimeBodyPart = new MimeBodyPart();
           mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

           Multipart multipart = new MimeMultipart();
           multipart.addBodyPart(mimeBodyPart);

           message.setContent(multipart);

           Transport.send(message);
       } catch (MessagingException | IOException e ){
           //TODO: include Exception msg
       }

    }
}
