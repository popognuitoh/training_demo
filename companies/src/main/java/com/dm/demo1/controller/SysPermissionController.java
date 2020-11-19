package com.dm.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ---------------------------
 * (SysUserController) 系统权限控制器
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/2
 * @Version: [1.0.1]
 * ---------------------------
 */
@Controller
@RequestMapping("/permission")
public class SysPermissionController {

    private static final String HTML_PREFIX = "system/permission/";

    @GetMapping(value = {"/","","/index"})
    public String user(){
        return HTML_PREFIX + "permission-list";
    }
}
