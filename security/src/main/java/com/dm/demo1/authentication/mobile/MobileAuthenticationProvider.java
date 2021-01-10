package com.dm.demo1.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ---------------------------
 * (MobileAuthenticationProvider) 手机认证处理提供者
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 认证处理
     * 1.通过手机号码查询用户信息  基于(UserDetailsService)实现
     * 2.当查询到用户信息  则认为认证通过
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken =
                (MobileAuthenticationToken) authentication;
        //获取未认证前的手机号码
        String mobile = (String) mobileAuthenticationToken.getPrincipal();
        //通过手机号码查询用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        if (userDetails == null){
            //未通过认证
            throw  new AuthenticationServiceException("未查询到用户信息");
        }
        //认证通过后
        //把它封装到MobileAuthenticationToken里边
        MobileAuthenticationToken authenticationToken =
                new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    /**
     * 通过方法来选择对应的provider 即选择 MobileAuthenticationProvider
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        /**
         * 判断传过来的类型是否支持
         */
        return (MobileAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
