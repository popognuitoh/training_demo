package com.dm.demo1.security.authorize;


import com.dm.demo1.authorize.AuthorizeConfigureProvider;
import com.dm.demo1.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * (SystemAuthorizeConfigureProvider) 系统的权限认证统一管理授权认证
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/3
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component
public class SystemAuthorizeConfigureProvider implements AuthorizeConfigureProvider {

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 实现配置
     * @param config
     */
    @Override
    public void confiure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
         config.antMatchers("/user").hasAuthority("sys:user")
                //有get权限得sys:role 可以访问/role
                .antMatchers(HttpMethod.GET,"/role").hasAuthority("sys:role")
                .antMatchers(HttpMethod.GET,"/permission").access("hasAuthority('sys:permission') or hasAnyRole('ADMIN','ROOT')");
    }
}
