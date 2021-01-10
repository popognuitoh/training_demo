package com.dm.demo1.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * (SecurityProperties) 读取application中的配置
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component // 不要少了
@ConfigurationProperties(prefix = "hezhou.security")
public class SecurityProperties {

    // 会将 hezhou.security.authentication 下面的值绑定到AuthenticationProperties对象上
    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}


