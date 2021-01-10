package com.dm.demo1.authentication.mobile;

import com.dm.demo1.authentication.CustomAuthenticationFailureHanlder;
import com.dm.demo1.authentication.exception.ValidateCodeException;
import com.dm.demo1.controller.MobileLoginController;
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
 * (MobileValidateFilter) 图形验证码检验
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component  //不要少了
public class MobileValidateFilter extends OncePerRequestFilter {

    /**
     * 失败回调处理器
     */
    @Autowired
    CustomAuthenticationFailureHanlder customAuthenticationFailureHanlder;

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 校验用户输入的手机验证码是否正确
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //忽略其他请求  只有认证手机短息才进行验证
        //判断登陆方式是否为手机登陆
        if (securityProperties.getAuthentication().getLoginMobileUrl().equalsIgnoreCase(request.getRequestURI())
                && "post".equalsIgnoreCase(request.getMethod())){
            try{
                //需要认证 看是检验
                isNeedValidateCode(request);
            }
            catch (AuthenticationException e){
                //交给失败回调处理
                customAuthenticationFailureHanlder.onAuthenticationFailure(request,response,e);
                //退出校验
                return;
            }
        }
        //否则就放行
        filterChain.doFilter(request,response);
    }

    private void isNeedValidateCode(HttpServletRequest request) {
        //1.先去session中拿值
        String attribute = (String)request.getSession().getAttribute(MobileLoginController.SESSION_KEY);
        //判断值是否和传入的值相等
        String code = request.getParameter("code");
        if (ParamCheckUtils.paramIsNull(code)){
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (!code.equalsIgnoreCase(attribute)){
            throw new ValidateCodeException("验证码输入错误！");
        }
    }
}
