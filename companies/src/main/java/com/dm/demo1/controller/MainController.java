package com.dm.demo1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * ---------------------------
 * (ReqDesignateWorkOrder) 这是我web层的第一个测试controller
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/26
 * @Version: [1.0.1]
 * ---------------------------
 */
@Controller
public class MainController {

    /**
     * 1.获取用户认证信息
     * 哪里都可以获取 2.3只能在控制层
     * @param map
     * @return
     */
    @RequestMapping({"/index","/",""})
    public String index(Map<String,Object> map){
        // 会去找 resources/template/index.html
        //1.拿到登陆后的用户的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取到的用户信息
        Object result = authentication.getPrincipal();
        if (result!=null && result instanceof UserDetails){
           UserDetails userDetails =  (UserDetails)result;
           String userName = userDetails.getUsername();
           map.put("userName",userName);
        }
        return "index";
    }

    /**
     *  2.拿到当前登陆的信息
     */
    @ResponseBody
    @RequestMapping("/user/info")
    public Object userInfo(Authentication authentication){
        return authentication.getPrincipal();
    }

    /**
     *  3.拿到当前登陆的信息
     */
    @ResponseBody
    @RequestMapping("/user/info2")
    public UserDetails userInfo2(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
