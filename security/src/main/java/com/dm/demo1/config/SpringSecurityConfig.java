package com.dm.demo1.config;


import com.dm.demo1.authentication.code.ImageCodeValidateFilter;
import com.dm.demo1.authentication.mobile.MobileAuthenlicationConfig;
import com.dm.demo1.authentication.mobile.MobileValidateFilter;
import com.dm.demo1.authentication.session.CustomLogoutHandler;
import com.dm.demo1.authorize.AnthorizeConfigureManager;
import com.dm.demo1.base.HezhouResult;
import com.dm.demo1.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;


/**
 * alt+/ 导包
 * ctrl+o 覆盖
 * @Auther: [hezhou]
 */
@Configuration
@EnableWebSecurity // 开启springsecurity过滤链 filter
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解方法级别权限控制
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 为了解决退出重新登录问题
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 明文+随机盐值》加密存储
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 校验验证码的处理器
     */
    @Autowired
    private ImageCodeValidateFilter imageCodeValidateFilter;

    @Autowired
    private MobileValidateFilter mobileValidateFilter;

    /**
     * 手机号认证
     */
    @Autowired
    private MobileAuthenlicationConfig mobileAuthenlicationConfig;

@Autowired
private CustomLogoutHandler customLogoutHandler;

    /**
     * security的配置类
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 去web端拿的配置类  继承了userDetailsService loadUserByUsername方法
     */
    @Autowired
    UserDetailsService customUserDetailsService;

    @Autowired
    InvalidSessionStrategy invalidSessionStrategy;

    /**
     * 依赖注入  自己实现的成功认证处理  名字一定要是加入容器的 customAuthenticationSuccessHandler
     * 重写  AuthenticationSuccessHandler 方法
     */
    @Autowired
    AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    /**
     * 依赖注入  自己实现的成功认证处理  名字一定要是加入容器的 AuthenticationFailureHandler
     * 重写  customAuthenticationFailureHanlder 方法
     */
    @Autowired
    AuthenticationFailureHandler customAuthenticationFailureHanlder;

    @Autowired
    private AnthorizeConfigureManager anthorizeConfigureManager;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    /**
     * 认证管理器：
     * 1. 认证信息（用户名，密码）
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 数据库存储的密码必须是加密后的，不然会报错：There is no PasswordEncoder mapped for the id "null"
        String password = passwordEncoder().encode("123456");
        logger.info("加密之后存储的密码：" + password);
        auth.inMemoryAuthentication().withUser("amen1324")
                .password(password).authorities("ADMIN");
         auth.userDetailsService(customUserDetailsService);
    }

    /// 继承 hierarchy.
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    /**
     * 资源权限配置：
     * 1. 被拦截的资源
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//          http.httpBasic() // 采用 httpBasic认证方式
        http.addFilterBefore(mobileValidateFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class) //把图片验证码校验放在用户名密码过滤器校验之前
            .formLogin() // 表单登录方式
            .loginPage(securityProperties.getAuthentication().getLoginPage()) // 设置自定义的登陆页面
            .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl()) //处理登陆表单的拦截地址
            .usernameParameter(securityProperties.getAuthentication().getUsernameParameter())
            .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())
            .successHandler(customAuthenticationSuccessHandler)  //登陆认证成功后的处理 这里自己手动定义的
            .failureHandler(customAuthenticationFailureHanlder) //登陆认证失败处理器
//            .and().authorizeRequests()
//                .antMatchers(securityProperties.getAuthentication().getLoginPage(),
//                    securityProperties.getAuthentication().getImageCode(),
//                    securityProperties.getAuthentication().getMobilePage(),
//                    securityProperties.getAuthentication().getCodeMobole()).permitAll()
//    //                .antMatchers("/user").hasRole("ADMIN") //有管理员权限才能访问这个 //设置角色时 会添加ROLE_
//                //有sys:user得可以访问任意请求得/user
//                .antMatchers("/user").hasAuthority("sys:user")
//                //有get权限得sys:role 可以访问/role
//                .antMatchers(HttpMethod.GET,"/role").hasAuthority("sys:role")
//                .antMatchers(HttpMethod.GET,"/permission").access("hasAuthority('sys:permission') or hasAnyRole('ADMIN','ROOT')")
//                .anyRequest().authenticated() //所有访问该应用的http请求都要通过身份认证才可以访问
            .and().rememberMe().tokenRepository(jdbcTokenRepository()) //保存登陆的信息
            .tokenValiditySeconds(securityProperties.getAuthentication().getTokenValiditySeconds()) //保存登陆信息的时间  有效时长 一个星期
            // 注意不要少了分号
            .and().sessionManagement()//设置session的管理器
            .invalidSessionStrategy(invalidSessionStrategy)   //当session过期后的处理
            .maximumSessions(1) //限制每个用户最多存在一个SESSION  当你另一个浏览器登陆时  默认只会显示一个字符串
            .expiredSessionStrategy(sessionInformationExpiredStrategy) //自定义多台登陆时的处理逻辑
            .maxSessionsPreventsLogin(true)//如果一个用户登录了  那么下次就不能登录了
            .sessionRegistry(sessionRegistry())
            .and()
                .and().logout()
                .addLogoutHandler(customLogoutHandler)//退出等率处理
                .logoutUrl("/user/logout").logoutSuccessUrl("/mobile/page") //退出成功后跳转的地址
                .deleteCookies("JSESSIONID"); //退出成功后删除jsession
        //将手机认证添加到过滤器链上
        http.apply(mobileAuthenlicationConfig);
        http.csrf().disable(); //关闭跨站 请求伪造(例如 退出登录可以接受get请求)

//        //授权统一管理配置 有这个  上边的都不需要了
        anthorizeConfigureManager.configure(http.authorizeRequests());
    }

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 记住我功能
     * remember-me  会生成一个字符  前端application 下的cookieanpvbFklMkZodCUyQklXZFJNZlQlMkI0N1hMQSUzRCUzRDpvdzQ0YXVkUk1tTERGVDhySURvUWJnJTNEJTNEs中可以查看
     * --[anpvbFklMkZodCUyQklXZFJNZlQlMkI0N1hMQSUzRCUzRDpvdzQ0YXVkUk1tTERGVDhySURvUWJnJTNEJTNE]
     * 解码后  中保存了用户名 序列号 token
     * 通过 序列号查询数据库  看用户名和token是否匹配  匹配直接放行  通过认证
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //是否启动项目时自动创建表-true自动创建
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 图片资源过滤
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());
    }
}
