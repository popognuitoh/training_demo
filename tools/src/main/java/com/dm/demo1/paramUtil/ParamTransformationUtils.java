package com.dm.demo1.paramUtil;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该工具类的各种方法接收参数并进行转型，如果参数不能转为指定类型，返回null
 */
public class ParamTransformationUtils {

    /**
     * 转型为字符串
     *
     * @param param
     * @return
     */
    public static String transformToString(Object param) {
        if (param == null) {
            return null;
        }
        if (param instanceof String) {
            return param.toString();
        }
        return param.toString();

    }

    /**
     * 转型为非负Integer
     *
     * @param param
     * @return
     */

    public static Integer transformToNonNegativeInteger(Object param) {
        if (param == null) {
            return null;
        }
        if (param instanceof Integer) {
            return (Integer) param;
        }
        String string = param.toString();
        //正则表达式，匹配非负整数，前面可以为0，比如00001
        String regEx = "^[0-9]\\d*|0$";
        //判断字符串是否与正则表达式相匹配
        if (stringCheck(string, regEx)) {
            return Integer.valueOf(string);
        }
        return null;
    }

    /**
     * 转型为Integer
     * @param param
     * @return
     */

    public static Integer transformToInteger(Object param) {
        if (param == null) {
            return null;
        }
        if (param instanceof Integer) {
            return (Integer) param;
        }
        String string = param.toString();
        //正则表达式，匹配非负整数
        String regEx = "^-?[0-9]\\d*$";
        //判断字符串是否与正则表达式相匹配
        if (stringCheck(string, regEx)) {
            return Integer.valueOf(string);
        }
        return null;
    }


    /**
     * 转型为非负Long
     * @param param
     * @return
     */
    public static Long transformToNonNegativeLong(Object param) {
        if (param == null) {
            return null;
        }
        String string = param.toString();
        //正则表达式，匹配非负整数
        String regEx = "^[0-9]\\d*|0$";
        //判断字符串是否与正则表达式相匹配
        //这里不能将支付宝回调的参数转型，原因不明
        if (stringCheck(string, regEx)) {
            return Long.valueOf(string);
        }
        return null;
    }

    /**
     * 转型为非负小数
     *
     * @param param
     * @return
     */

    public static BigDecimal transformToNonNegativeBigDecimal(Object param) {
        if (param == null) {
            return null;
        }
        if (param instanceof BigDecimal) {
            return (BigDecimal) param;
        }
        String string = param.toString();
        //正则表达式，匹配非负小数
        String regEx = "^\\d+(\\.\\d+)?$";
        if (stringCheck(string, regEx)) {
            return new BigDecimal(string);
        }
        return null;
    }

    /**
     * 判断字符串是否匹配正则表达式
     *
     * @param str
     * @param regEx
     * @return
     */
    private static boolean stringCheck(String str, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //测试代码
    public static void main(String[] args) {
        Object o1 = "000010000018 ";
        System.out.println(transformToNonNegativeLong(o1));
//        System.out.println(Long.valueOf(o1.toString()));
    }
}
