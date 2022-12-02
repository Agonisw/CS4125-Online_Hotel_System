package com.xxx.common.utils;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class MailUtil {

    public static void sendMail(String host,String userName,String password,String from,String to,String subject,String text) throws MessagingException,UnsupportedEncodingException {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        senderImpl.setHost(host);
        senderImpl.setUsername(userName);
        senderImpl.setPassword(password);
        //Create a mail message and send the difference between simple mail and html mail
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        messageHelper.setText(text);
        //Setting this parameter to true allows the server to authenticate that the user name and password are correct
        Properties prop= new Properties();
        prop.put("mail.smtp.auth","true");
        //busy timeout duration
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        //Send email
        senderImpl.send(mailMessage);
    }
}
