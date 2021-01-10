package com.dm.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * ---------------------------
 * (ReqDesignateWorkOrder) springSecurity中提示信息的修改
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Configuration
public class ReloadMessageConfig {

    /**
     * 更改错误的认证信息
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //不要加载到.properties 因为路径会在set时自动拼接  现在找到中文配置文件并把它改写成这个
        //如果你想修改提示信息  就可以复制springSecurity下的这个文件到resource下然后修改setBaseName地址路径
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return messageSource;
    }
}
