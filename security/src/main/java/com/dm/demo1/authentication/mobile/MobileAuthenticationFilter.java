package com.dm.demo1.authentication.mobile;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ---------------------------
 * (MobileAuthenticationFilter) 校验用户的手机号是否通过认证
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 请求的传入参数
     */
    private String mobileParameter = "mobile";

    /**
     * 传入的验证码
     */
    private String codeParameter = "code";

    private boolean postOnly = true;

    /**
     * 声明请求地址和请求方式
     */
    protected MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher("/mobile/form","POST"));
    }

    protected MobileAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected MobileAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    /**
     * 重写它的认证方法
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        //确保必须是post请求
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        //拿到code 和 电话
        String mobile = obtainMobile(request);
//        String code = obtainCode(request);

        //100%转字符
        if (mobile == null) {
            mobile = "";
        }
//
//        if (code == null) {
//            code = "";
//        }
        //去空格
        mobile = mobile.trim();

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(mobile);

        // Allow subclasses to set the "details" property
        setDetails(request, authenticationToken);

        //构造一个token
        return this.getAuthenticationManager().authenticate(authenticationToken);

    }

    /**
     * 将sessionId hostName添加至details
     * @param request
     * @param mobileAuthenticationToken
     */
    protected void setDetails(HttpServletRequest request,
                              MobileAuthenticationToken mobileAuthenticationToken) {
        mobileAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public String getMobileParameter() {
        return mobileParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        this.mobileParameter = mobileParameter;
    }

    /**
     * 获取手机号
     * @param request
     * @return
     */
    @Nullable
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    /**
     * 获取手机验证码
     * @param request
     * @return
     */
    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    /**
     * 设置是否为post请求
     * @param postOnly
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
