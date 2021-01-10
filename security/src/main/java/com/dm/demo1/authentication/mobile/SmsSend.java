package com.dm.demo1.authentication.mobile;

/**
 * ---------------------------
 * (ValidateCodeException) 定义发送验证码的接口
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
public interface SmsSend {

    /**
     * 发送短信
     * @param mobile 手机号
     * @param content 发送的内容
     * @return true 表示发送成功，false发送失败
     */
    boolean sendSms(String mobile, String content);
}
