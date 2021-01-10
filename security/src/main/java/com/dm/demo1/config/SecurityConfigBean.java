package com.dm.demo1.config;


import com.dm.demo1.authentication.mobile.SmsCodeSender;
import com.dm.demo1.authentication.mobile.SmsSend;
import com.dm.demo1.authentication.session.CustomInvalidSessionStrategy;
import com.dm.demo1.authentication.session.CustomSessionInformationExpiredStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;

/**
 * ---------------------------
 * (SecurityConfigBean) 类的配置bean 为的是实现可扩展
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
@Configuration
public class SecurityConfigBean {

    @Resource
    private SessionRegistry sessionRegistry;

    /**
     * 发送短信的处理类
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsSend.class)  //如果当前容器中已经有电脑bean了，就不注入备用电脑，如果没有，则注入备用电脑
    public SmsSend smsSend(){
        return new SmsCodeSender();
    }

    /**
     * 当session失效后的处理类
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new CustomInvalidSessionStrategy(sessionRegistry);
    }

    /**
     * 多端登录时的处理 如果bean中没有  注入我自定义的bean 如果容器中尤其它实现 就不会生效
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new CustomSessionInformationExpiredStrategy();
    }
}
