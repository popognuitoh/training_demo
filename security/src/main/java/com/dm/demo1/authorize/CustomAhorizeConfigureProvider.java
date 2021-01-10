package com.dm.demo1.authorize;

import com.dm.demo1.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * (CustomAhorizeConfigureProvider) 统一配置接口实现类
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/3
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component
@Order(Integer.MAX_VALUE) //值越小加载越靠前   值越大加载最慢  放最后配置
public class CustomAhorizeConfigureProvider implements AuthorizeConfigureProvider {

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 关于身份认证的授权配置 实现他的配置方法
     * @param config
     */
    @Override
    public void confiure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config .antMatchers(securityProperties.getAuthentication().getLoginPage(),
                securityProperties.getAuthentication().getImageCode(),
                securityProperties.getAuthentication().getMobilePage(),
                securityProperties.getAuthentication().getCodeMobole(),
                securityProperties.getAuthentication().getAdminTest1())
                .permitAll()
                .antMatchers(securityProperties.getAuthentication().getAdminTest2()).hasAnyAuthority("ADMIN")
        ;

        //所有请求都要通过身份认证
        config.anyRequest().permitAll();
    }
}
