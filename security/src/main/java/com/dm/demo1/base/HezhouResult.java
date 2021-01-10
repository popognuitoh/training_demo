package com.dm.demo1.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * ---------------------------
 * (HezhouResult) 返回前端的JSON响应信息类
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Data
public class HezhouResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public HezhouResult() {
    }
    public HezhouResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public HezhouResult(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public HezhouResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static HezhouResult ok() {
        return new HezhouResult(null);
    }
    public static HezhouResult ok(String message) {
        return new HezhouResult(message, null);
    }
    public static HezhouResult ok(Object data) {
        return new HezhouResult(data);
    }
    public static HezhouResult ok(String message, Object data) {
        return new HezhouResult(message, data);
    }

    public static HezhouResult build(Integer code, String message) {
        return new HezhouResult(code, message, null);
    }

    public static HezhouResult build(Integer code, String message, Object data) {
        return new HezhouResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 HezhouResult 对象
     * @param json
     * @return
     */
    public static HezhouResult format(String json) {
        try {
            return JSON.parseObject(json, HezhouResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
