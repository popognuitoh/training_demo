package com.dm.demo1.properties;

/**
 * ---------------------------
 * (LoginResponseType) 认证响应的类型
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
public enum LoginResponseType {
    /**
     * 响应json
     */
    JSON,

    /**
     * 响应发送的请求 重定向到地址
     */
    REDIRECT
}
