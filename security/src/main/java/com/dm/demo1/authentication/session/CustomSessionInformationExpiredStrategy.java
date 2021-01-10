package com.dm.demo1.authentication.session;

import com.dm.demo1.authentication.CustomAuthenticationFailureHanlder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * ---------------------------
 * (CustomSessionInformationExpiredStrategy) 当同一个用户的session达到指定数量的时候
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/29
 * @Version: [1.0.1]
 * ---------------------------
 */
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomAuthenticationFailureHanlder customAuthenticationFailureHanlder;

    /**
     * 当同个用户在不通浏览器登陆时  而我们的session最大数量为1时 进到这个逻辑处理
     * @param event
     * @throws IOException
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {

        logger.info("现在触发session超过最大数量的事件");
        //获取用户名
        Object obj = event.getSessionInformation().getPrincipal();
        UserDetails userDetails = null;
        if (obj!=null && obj  instanceof UserDetails){
            userDetails = (UserDetails)obj;
        }
        AuthenticationException exception = new AuthenticationServiceException(
               String.format("[%s] 用户在另一台电脑已经登录了！ 您已被迫下线",userDetails.getUsername()));
        try {
            //指定在用户这次刷新时去认证的页面
            event.getRequest().setAttribute("toAuthentication",true);
            customAuthenticationFailureHanlder.onAuthenticationFailure(event.getRequest(),event.getResponse(),exception);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}