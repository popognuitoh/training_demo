package com.dm.demo1.properties;

import lombok.Data;

/**
 * ---------------------------
 * (SecurityProperties) 读取application中的配置
 * ---------------------------
 *this.getUserDetailsService()
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Data
public class AuthenticationProperties {




    private String loginPage = "/login/page";
    private String loginProcessingUrl = "/login/form";
    private String loginMobileUrl = "/mobile/form"; //手机登陆的地址
    private String usernameParameter = "name";
    private String passwordParameter = "pwd";
    private Integer tokenValiditySeconds = 604800;
    //    imageCode: /image/code #图片验证码
    private String imageCode = "/image/code";
    //    mobilePage: /mobile/page #手机认证页面
    private String mobilePage = "/mobile/page";
    //    codeMobole: /code/mobile #电话验证码
    private String codeMobole = "/code/mobile";
    private String[] staticPaths = {"/dist/**", "/modules/**", "/plugins/**"};
    private String adminTest1 = "/admin/test1";
    private String adminTest2 = "/admin/test2";
    private String adminTest3 = "/admin/test3";

    //响应的类型 json/redirect
    private LoginResponseType loginType = LoginResponseType.JSON;

    public String getLoginPage() {
        return loginPage;
    }
}
