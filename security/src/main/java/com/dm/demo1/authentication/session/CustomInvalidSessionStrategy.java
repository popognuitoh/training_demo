package com.dm.demo1.authentication.session;

import com.dm.demo1.base.HezhouResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ---------------------------
 * (CustomInvalidSessionStrategy) 自定义的session失效后的处理逻辑
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/29
 * @Version: [1.0.1]
 * ---------------------------
 */
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    Logger logger = LoggerFactory.getLogger(getClass());

    private SessionRegistry sessionRegistry;

    public CustomInvalidSessionStrategy(SessionRegistry sessionRegistry){
        this.sessionRegistry = sessionRegistry;
    }

    /**
     * session过期后触发的事件
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取当前的session  如果有就会打印出来  没有就会新创建
        logger.info("sessionId"+request.getSession().getId());
        String requestedSessionId = request.getRequestedSessionId();
        logger.info("这是获取的时用户登录的时 application 中的requestedSessionId:++"+requestedSessionId);
        //清除缓存中的数据
        sessionRegistry.removeSessionInformation(requestedSessionId);

        //1.清除浏览器Cookies中的JsessionId  bu然一直意识已超时
        cancelCookie(request,response);
        HezhouResult hezhouResult = HezhouResult.build(HttpStatus.UNAUTHORIZED.value(),"登录已经超时  请重新登录！");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(hezhouResult.toJsonString());
    }

    /**
     * 清楚浏览器中的cookies
     * @param request
     * @param response
     */
    protected void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Cancelling cookie");
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath(getCookiePath(request));
        response.addCookie(cookie);
    }

    /**
     * 获取cookies的路径
     * @param request
     * @return
     */
    private String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
}
