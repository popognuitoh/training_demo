package com.dm.demo1.authentication;

        import com.alibaba.fastjson.JSON;
        import com.dm.demo1.base.HezhouResult;
        import com.dm.demo1.properties.LoginResponseType;
        import com.dm.demo1.properties.SecurityProperties;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
        import org.springframework.stereotype.Component;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

/**
 * ---------------------------
 * (CustomAuthentication) 自定义的处理器  认证成功处理
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/27
 * @Version: [1.0.1]
 * ---------------------------
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 实现登陆成功的处理器 继承实现方法 AuthenticationSuccessHandler
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info(JSON.toJSONString(securityProperties.getAuthentication().getLoginType()));
        if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())){
            logger.info("authentication", JSON.toJSONString(authentication));
            //认证成功后相应JSON字符串
            //HezhouResult hezhouResult = HezhouResult.ok("验证成功");
            HezhouResult hezhouResult = HezhouResult.ok(authentication);
            String jsonString = hezhouResult.toJsonString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jsonString);
        }else{
            logger.info("authentication:"+ JSON.toJSONString(authentication));
            //重定向到上次请求的地址，引发跳转到人认证页面的地址
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
