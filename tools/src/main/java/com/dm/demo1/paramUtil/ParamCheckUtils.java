package com.dm.demo1.paramUtil;

import com.dm.demo1.entity.AboutCity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ParamCheckUtils {
    /**
     * 如果有任意参数为空，返回true
     * @param args
     * @return
     */
    public static Boolean paramIsNull(Object... args) {
        for (Object arg : args) {
            if (arg == null)
                return true;
        }
        return false;
    }

    /**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz){
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.lang.Long")) {
                    Object object =map.get(field.getName());
                    field.set(obj,object!=null?Long.valueOf(object.toString()):null);
                }else if (filedTypeName.equalsIgnoreCase("java.math.BigDecimal")){
                    field.set(obj, new BigDecimal(map.get(field.getName()).toString()).setScale(2, BigDecimal.ROUND_DOWN));
                }else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return obj;
    }

    //测试代码
    public static void main(String[] args) {

//        System.out.println(paramIsNull(22, 1234));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("cityCode",1222);
        objectMap.put("cityNameCn","中国");
        objectMap.put("cityDescription","dddadfdfdfdfsd");
        objectMap.put("cityVideoDescription","cityVideoDescription.mp4");


        Object object1 = map2Object(objectMap,AboutCity.class);

        System.out.println("==================================="+object1);
    }
}
