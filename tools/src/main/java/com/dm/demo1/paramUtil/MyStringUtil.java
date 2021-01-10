package com.dm.demo1.paramUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtil {
    /**
     * 把字符串中的数字全部提取到一起
     * @param str
     * @return
     */
    public static Long getNumberFromString(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String result = m.replaceAll("").trim();
        return Long.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(getNumberFromString("xy213546434df34"));
    }
}
