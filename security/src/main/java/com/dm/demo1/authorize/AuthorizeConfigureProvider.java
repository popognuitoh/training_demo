package com.dm.demo1.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * ---------------------------
 * (AuthorizeConfigProvider) 授权配置统一接口  所有模块的都要实现这个接口、
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/3
 * @Version: [1.0.1]
 * ---------------------------
 */
public interface AuthorizeConfigureProvider {
    void confiure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
