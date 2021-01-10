package com.dm.demo1.security.AllUserDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * (CustomUserDetailsService) 用户登录动态实现
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PasswordEncoder passwordEncoder;
    /**
     * 实现用户密码动态登陆
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //1.去数据库 查询用户的信息
        logger.info("请求认证的用户名:"+username);
        if (!"hezhou".equals(username)){
            throw  new UsernameNotFoundException("用户名或者密码错误");
        }


        //从数据库中拿到的密码 假设时123456
        String password= passwordEncoder.encode("123456");
        //2.查询该用户有那些权限

        //3.封装用户信息和权限信息
       User user = new User(username,password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("sys:user,sys:role,sys:user:add")); //设置权限集合  他有的权限
        return user;
    }
}
