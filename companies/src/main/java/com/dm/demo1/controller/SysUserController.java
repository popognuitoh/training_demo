package com.dm.demo1.controller;

import com.dm.demo1.base.HezhouResult;
import org.assertj.core.util.Lists;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ---------------------------
 * (SysUserController) 系统用户控制器
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/3/2
 * @Version: [1.0.1]
 * ---------------------------
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final String HTML_PREFIX = "system/user/";

    @PreAuthorize("hasAuthority('sys:user')")
    @GetMapping(value = {"/","","/index"})
    public String user(){
        return HTML_PREFIX + "user-list";
    }

    /**
     * PreAuthorize 在调用这个方法之前
     * 跳转到新增或者修改页面 有这两个el表达式其中一个的 都能执行这个方法
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:user:add','sys:user:edit')")
    @GetMapping(value = {"/form"})
    public String form(){
        return HTML_PREFIX+"user-form";
    }

    /**
     * PostAuthorize 在调用这个方法之后
     * 返回值的code == 200 才会返回回去  不然会报403
     * 有这个删除权限的才能用这个方法
     * @return
     */
    @PostAuthorize("returnObject.code == 200")
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public HezhouResult deleteById(@PathVariable Integer id){
        if (id<0){
            return HezhouResult.build(500,"id不能小于零");
        }
        return HezhouResult.ok();
    }


    /**
     * PreFilter 方法之前过滤 小于等于0 的参数
     * filterTarget 只当是哪个参数
     * 批量根据id删除
     * @return
     */
    @PreFilter(filterTarget = "ids",value = "filterObject > 0")
    @RequestMapping(value = "/batch/{ids}") //user/batch/-1,0,1,2
    @ResponseBody
    public HezhouResult deleteById(@PathVariable List<Long> ids){
        return HezhouResult.ok(ids);
    }

    /**
     * 过滤返回值  当filterObject = true时就返回  否则就过滤
     * 满足就返回  不满足就不返回
     * 这里是在集合里判断是不是当前登陆的
     * 在方法之后
     * @return
     */
    @PostFilter("filterObject != authentication.principal.username")
    @RequestMapping("/list")
    @ResponseBody
    public List<String> page(){
        List<String> list = Lists.newArrayList("hezhou","zhou","father");
        return list;
    }
}
