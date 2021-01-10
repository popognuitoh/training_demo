package com.dm.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dm.demo1.authentication.Mail.MailService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/mail/")
public class MailController {

    @Resource
    MailService mailService;

    @GetMapping("testMail")
    @ResponseBody
    public String testMail(@RequestBody String to){
        String object = "发送邮件功能测试";
        String code = "XMSH";
        mailService.sendMail(to,object,code);
        return "已测试";
    }
}
