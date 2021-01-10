package com.dm.demo1.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ---------------------------
 * (ReqDesignateWorkOrder) 自己定义的登陆页面
 * ---------------------------
 *
 * @Author: [hezhou]
 * @Date: 2020/2/26
 * @Version: [1.0.1]
 * ---------------------------
 */
@Controller
public class CustomLoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static  final  String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @RequestMapping("/page")
    @ResponseBody
    public String toLogin(){
        //classpath: /template/login.html
        return "success";
    }

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 获取图形验证码
     */
    @RequestMapping("/image/code")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取验证码字符串
        String code = defaultKaptcha.createText();
        logger.info("生成的图形验证吗是"+code);
        //2.把字符串存到session中
        request.getSession().setAttribute(SESSION_KEY,code);
        //3.获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);

        //4.把图片通过流写出去
        try{
            ServletOutputStream outputStream = response.getOutputStream();
            //5.利用io工具类 将图片写出去
            ImageIO.write(image,"jpg",outputStream);
        }catch (Exception e){
            throw new IIOException("写出失败"+e.getMessage());
        }
    }
}
