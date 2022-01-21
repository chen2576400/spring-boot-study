package com.chenning.springbootlearn.reflex.demon1;

import java.lang.reflect.Method;

/**
 * @author: Mr.Chen
 * @create: 2021-02-04 17:08
 * 返回mentod
 **/
public class MethodUntil {

    /**
     * @RequestMapping("zz") public String x(String a) {
     * return (a);
     * }
     * <p>
     * public text(){
     * Method method=null;
     * try {
     * //第一个参数是具体调用该方法的对象
     * //第二个参数是执行该方法的具体参数
     * method= this .getClass().getDeclaredMethod("x", String.class);    //这里的this代表当前controller（其他页面的可以用SpringContextUtils.getBean(beanName)获取对象）    x代表方法名
     * } catch (NoSuchMethodException e) {
     * e.printStackTrace();
     * }
     * try {
     * //第一个参数为类的实例(就是该method（方法）在哪个类里面)
     * //第二个参数为相应函数中的参数
     * method.invoke(this,"1111");                       //执行此方法
     * } catch (IllegalAccessException e) {
     * e.printStackTrace();
     * } catch (InvocationTargetException e) {
     * e.printStackTrace();
     * }
     * <p>
     * }
     */


    /**
     *
     * @param clazz  类名
     * @param name   方法名
     * @param paratmeterTypes  参数
     * @return
     */
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
