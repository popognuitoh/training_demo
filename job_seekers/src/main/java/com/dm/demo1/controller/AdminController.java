package com.dm.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.dm.demo1.entity.AboutCity;
import com.dm.demo1.entity.UserAccount;
import com.dm.demo1.entity.WorldCountriesCities;
import com.dm.demo1.paramUtil.ParamCheckUtils;
import com.dm.demo1.service.WorldCountriesCitiesService;
import com.github.pagehelper.PageInfo;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatMpRequest;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Resource
    WorldCountriesCitiesService worldCountriesCitiesService;

    @GetMapping("test1")
    @ResponseBody
    public String test1(){
        return "je suis la test1 ";
    }

    @RequestMapping("/test2")
    public String toLogin(){
        //classpath: /template/login.html
        return "je suis la test2";
    }

    @RequestMapping("/getText")
    @ResponseBody
    public PageInfo<WorldCountriesCities> getText(@RequestBody JSONObject json){
        Integer page = json.getInteger("page")!=null?json.getInteger("page"):1;
        Integer size = json.getInteger("size")!=null?json.getInteger("size"):10;

        PageHelper.startPage(page, size);
        List<WorldCountriesCities> list = worldCountriesCitiesService.list();
        PageInfo<WorldCountriesCities> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @RequestMapping("/test3")
    @ResponseBody
    public Map<String,Object> annotationTest(@RequestBody Map<String,Map<String,Object>> map){

        Map<String,Object> aboutCityMap = map.get("aboutCity");
        Map<String,Object> userAccountMap = map.get("userAccount");


        AboutCity aboutCity = (AboutCity) ParamCheckUtils.map2Object(aboutCityMap,AboutCity.class);
        UserAccount userAccount = (UserAccount) ParamCheckUtils.map2Object(userAccountMap,UserAccount.class);

//        System.out.println("=====================userAccount:"+  userAccount.toString());
        Map<String,Object> map1 = new HashMap<>();
        map1.put("aboutCity",aboutCity);
        map1.put("userAccount",userAccount);

        return map1;
    }



    public static void main(String[] args){
        File source = new File("C:\\Users\\520\\Desktop\\uploadTestFiles\\video3.mp4");
        File target = new File("C:\\Users\\520\\Desktop\\uploadTestFiles\\videos\\video3.mp4");


        try {
            Thumbnails.of(new File("C:\\Users\\520\\Desktop\\uploadTestFiles\\image1.jpg")).size(1500,1000).toFile(new File("C:\\Users\\520\\Desktop\\uploadTestFiles\\images\\image1.jpg"));
            System.out.println("====================================== ok1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
