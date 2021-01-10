package com.dm.demo1.controller;


import com.dm.demo1.authentication.mobile.SmsSend;
import com.dm.demo1.base.HezhouResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ---------------------------
 * (MobileLoginController) 管理手机登陆的控制层
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */

@Controller
public class MobileLoginController {

    public static final String SESSION_KEY = "SESSION_KEY_MOBILE_CODE";

    @Autowired
    SmsSend smsSend;

    /**
     * 前往手机验证码的登录页
     * @return
     */
    @RequestMapping(value = "/mobile/page")
    public String toMobilePage(){
        return "login-mobile"; //对应 template/login-mobile
    }


    /**
     * 发送手机验证码
     * @return
     */
    @RequestMapping(value = "/code/mobile")
    @ResponseBody  //会将对象转换为一个json
    public HezhouResult smsCode(HttpServletRequest request){
        //1.生成一个手机验证码
        String random = RandomStringUtils.randomNumeric(4);
        //2.将手机验证码保存到session中
        request.getSession().setAttribute(SESSION_KEY,random);
        //3.调用接口发送验证码到用户手机
        String mobile = request.getParameter("mobile");
        smsSend.sendSms(mobile,random);
        return HezhouResult.ok();
    }
}
