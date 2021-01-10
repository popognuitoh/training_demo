package com.dm.demo1.authentication.mobile;

import com.dm.demo1.authentication.CustomAuthenticationFailureHanlder;
import com.dm.demo1.authentication.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ---------------------------
 * (MobileAuthenlicationConfig) 用于组合其他关于手机登陆的组件
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component(value = "mobileAuthenlicationConfig")
public class MobileAuthenlicationConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHanlder customAuthenticationFailureHanlder;

    @Autowired
    UserDetailsService mobileUserDetailsService;

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //创建过滤器实例
        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();

        //设置记住我功能
        mobileAuthenticationFilter.setRememberMeServices(http.getSharedObject(RememberMeServices.class));
        //获取容器中已经存在的AuthenticationManager对象,并传入mobileAuthenticationFilter里边
        mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        //设置手机验证码 登录只允许一台机器上登录的处理逻辑类
        mobileAuthenticationFilter.setSessionAuthenticationStrategy(http.getSharedObject(SessionAuthenticationStrategy.class));
        //设置成功和失败处理器
        mobileAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        mobileAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHanlder);
        //构建一个mobileAuthenticationProvider实例，接受mobileAuthenticationProvider通过手机号查询信息
        MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
        mobileAuthenticationProvider.setUserDetailsService(mobileUserDetailsService);
        //将provider绑定到httpSecurity上  并将手机号认证过滤器绑定到用户名密码认证过滤器之后
        http.authenticationProvider(mobileAuthenticationProvider).addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
