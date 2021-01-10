package com.dm.demo1.authentication;


import com.dm.demo1.base.HezhouResult;
import com.dm.demo1.properties.LoginResponseType;
import com.dm.demo1.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ---------------------------
 * (CustomAuthenticationFailureHanlder) 认证失败处理器
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Data
@Component("customAuthenticationFailureHanlder")
//public class CustomAuthenticationFailureHanlder implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHanlder extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 认证失败的处理方法
     * 能跳转页面  可以返回json
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())){
            HezhouResult build = HezhouResult.build(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
            String s = build.toJsonString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(s);
        }else{
            // 1.获取一下上次的路径
            String referer = request.getHeader("Referer");
            Object toAuthentication = request.getSession().getAttribute("toAuthentication");
            //在多端登录request设置了值  如果时多端登录 就跳到登录地址
            String lastUrl = toAuthentication!= null?securityProperties.getAuthentication().getLoginPage(): StringUtils.substringBefore(referer, "?");
            logger.info("上一次请求的请求路径"+lastUrl);
            super.setDefaultFailureUrl(lastUrl+"?error");
            super.onAuthenticationFailure(request,response, exception);
        }
    }
}
