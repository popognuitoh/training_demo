package com.dm.demo1.authentication.Mail;

public interface MailService {
    void sendMail(String to, String subject, String verifyCode);
}
