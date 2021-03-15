package com.chenning.springbootlearn.reflex;

import java.lang.reflect.Method;

/**
 * @author: Mr.Chen
 * @create: 2021-02-04 17:08
 **/
public class MethodUntil {

    public static Method getMethod(Class<?> clazz, String name, Class<?>... paratmeterTypes) {
        Method method = null;
            try {
                method = clazz.getDeclaredMethod(name, paratmeterTypes);
            } catch (Exception e) {
                try {
                    method = clazz.getSuperclass().getDeclaredMethod(name, paratmeterTypes);//若是在子类找不到，就再去父类找一遍
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
            }



//        if (method==null){  //若是在子类找不到，就再去父类找一遍
//            try {
//                method=clazz.getSuperclass().getDeclaredMethod(name,paratmeterTypes);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
        return method;
    }
}
