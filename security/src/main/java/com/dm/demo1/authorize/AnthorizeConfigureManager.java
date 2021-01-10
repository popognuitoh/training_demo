package com.dm.demo1.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * ---------------------------
 * (AnthorizeConfigureManager) 统一授权管理接口
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/3
 * @Version: [1.0.1]
 * ---------------------------
 */
public interface AnthorizeConfigureManager {
    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
