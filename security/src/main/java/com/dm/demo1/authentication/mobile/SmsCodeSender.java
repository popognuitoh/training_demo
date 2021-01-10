package com.dm.demo1.authentication.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ---------------------------
 * (ValidateCodeException) 实现验证码的发送
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
//@Component
public class SmsCodeSender implements SmsSend {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param mobile 手机号
     * @param content 发送的内容: 接收的是验证码
     * @return
     */
    @Override
    public boolean sendSms(String mobile, String content) {
        logger.info("验证码为"+content);
        String sendContent = String.format("验证码%s, 请勿泄露给别人。", content);
        // 调用每三方发送功能的sdk
        logger.info("向手机号：" + mobile + "发送的短信为：" + sendContent);
        return true;
    }
}
