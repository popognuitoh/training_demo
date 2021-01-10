package com.dm.demo1.authentication.code;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * ---------------------------
 * (KaptchaImageCodeConfig) 第三方的图形验证码认证
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/28
 * @Version: [1.0.1]
 * ---------------------------
 */
@Configuration
public class KaptchaImageCodeConfig {

    /**
     * 自定义验证码
     */
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //设置验证码的规范
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "192,192,192");
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "110");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "36");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "28");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体");
        //生成几位数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 图片效果
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
