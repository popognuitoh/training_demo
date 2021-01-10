package com.dm.demo1.infoSender;

import com.dm.demo1.authentication.Mail.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomerMailSender implements MailService {

    @Resource
    private JavaMailSender mailSender;

    //邮件发件人
    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    public void sendMail(String to, String subject, String verifyCode) {
        //创建邮件正文
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText("您的code是："+verifyCode);
        try {
            mailSender.send(message);
            System.out.println("发送成功");
            //logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！"+ e);
        }
    }
}
