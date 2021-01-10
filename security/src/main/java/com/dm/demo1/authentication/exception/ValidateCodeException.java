package com.dm.demo1.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ---------------------------
 * (ValidateCodeException) 自定义的验证码为空的类
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
