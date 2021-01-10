package com.dm.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("test1")
    @ResponseBody
    public String test1(){
        return "je suis la test1 ";
    }

    @RequestMapping("/test2")
    public String toLogin(){
        //classpath: /template/login.html
        return "je suis la test2";
    }

    @RequestMapping("/test3")
    public String toLogin1(){
        //classpath: /template/login.html
        return "je suis la test3";
    }
}
