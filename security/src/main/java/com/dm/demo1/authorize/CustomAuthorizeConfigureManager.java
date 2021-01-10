package com.dm.demo1.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ---------------------------
 * (CustomAuthorizeConfigureManager) 统一配置授权管理类
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/3
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component
public class CustomAuthorizeConfigureManager implements AnthorizeConfigureManager{

    @Autowired
    List<AuthorizeConfigureProvider> authorizeConfigureProviderList;

    /**
     * 统一配置返回
     * 将一个个的AuthorizeConfigureProvider 传入参数 ExpressionInterceptUrlRegistry
     * @param config
     */
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //拿到所有的实现类
        for (AuthorizeConfigureProvider  provider: authorizeConfigureProviderList){
            //将配置传入实现类
            provider.confiure(config);
        }
    }
}
