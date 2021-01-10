package com.dm.demo1.authentication.code;

import com.dm.demo1.authentication.CustomAuthenticationFailureHanlder;
import com.dm.demo1.authentication.exception.ValidateCodeException;
import com.dm.demo1.controller.CustomLoginController;
import com.dm.demo1.paramUtil.ParamCheckUtils;
import com.dm.demo1.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ---------------------------
 * (ImageCodeValidateFilter) 自定义的验证码校验过滤器
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * OncePerRequestFilter ，每次请求调用一次
 * ---------------------------
 */
@Component(value = "imageCodeValidateFilter")
public class ImageCodeValidateFilter extends OncePerRequestFilter {

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    CustomAuthenticationFailureHanlder customAuthenticationFailureHanlder;
    /**
     * 每次请求调用一次
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //针对登陆请求进行验证 如果登陆请求就校验 && 是post请求
        // equalsIgnoreCase 忽略大小写 ！！！
        if (("/api"+ securityProperties.getAuthentication().getLoginProcessingUrl()).
                equals(request.getRequestURI()) && request.getMethod().equalsIgnoreCase("post")){
            //校验验证码合法性
           try{
               validate(request);
           }catch (AuthenticationException e){
               //交给失败处理器处理异常
               customAuthenticationFailureHanlder.onAuthenticationFailure(request,response,e);
               return;
           }
        }
        //放行请求
        filterChain.doFilter(request,response);
    }

    private void validate(HttpServletRequest request) {
        //先获取session中的验证码
        String attribute = (String)request.getSession().getAttribute(CustomLoginController.SESSION_KEY);
        //获取用户输入的
        String inputCode = request.getParameter("code");
        //用户输入是否为空
        if (ParamCheckUtils.paramIsNull(inputCode)){
            throw new ValidateCodeException("用户输入验证吗为空");
        }
        //忽略大小写校验
        if (!inputCode.equalsIgnoreCase(attribute)){
            throw new ValidateCodeException("验证码输入错误");
        }
    }
}
