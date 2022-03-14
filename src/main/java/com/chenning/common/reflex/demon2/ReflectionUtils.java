package com.chenning.common.reflex.demon2;

import java.lang.reflect.Constructor;

public class ReflectionUtils {

    public static <T> T newInstance(Class<T> clazz, Object... objects) {
        T t = null;
        try {
            Class[] parameterTypes = getParameterTypes(objects);
            t = clazz.getConstructor(parameterTypes).newInstance(objects);
        } catch (Exception e) {
            String errMsg = String.format("根据%s创建对象，发生找不到类异常！", clazz);
            throw new RuntimeException(errMsg);
        }
        return t;
    }

    /**
     * class.newInstance JDK1.9后被弃用，新的创建方法是调用Class类的getDeclaredConstructor()
     * 应该是根据传入的参数类型去调用相应的构造方法，而以前只能调用无参构造器
     *
     *
     * @param clazzName  类名
     * @param objects  构造函数参数
     * @return 对象类
     */
    public static Object newInstance(String clazzName, Object... objects) {
        Object instance = null;
        try {
            Class<?> clazz = Class.forName(clazzName);
            Class[] parameterTypes = getParameterTypes(objects);
            Constructor<?> constructor = clazz.getConstructor(parameterTypes);
            instance = constructor.newInstance(objects);
        } catch (Exception e) {
            String errorMsg = String.format("根据%s创建对象，发生找不到类异常！", clazzName);
            throw new RuntimeException(errorMsg);
        }
        return instance;
    }

    public static Class[] getParameterTypes(Object[] objects) {
        Class[] parameterTypes = null;
        if (objects != null && objects.length > 0) {
            parameterTypes = new Class[objects.length];
            for (int i = 0; i < objects.length; i++) {
                parameterTypes[i] = objects[i].getClass();
            }
        }
        return parameterTypes;
    }

}
