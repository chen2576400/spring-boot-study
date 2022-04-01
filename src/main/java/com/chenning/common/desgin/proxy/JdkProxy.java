package com.chenning.common.desgin.proxy;

import com.chenning.common.desgin.proxy.service.IHello;
import com.chenning.common.reflex.demon2.ReflectionUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-31 17:26
 **/
public class JdkProxy{

    /**
     *
     * 用其他对象来代理真实对象（interfaceClass 必须要有接口，并且接口的方法执行部分要保证和代理真实对象保持一致）
     *
     *
     * @param interfaceClass   建立中介类实例(最终InvocationHandler的method都会代理的是接口,所以传入的类必须要实现接口)
     *                       1：如getProxyWay001这里的代理中介类是全新的一个 cacheServerImpl,他这里和CacheAdapter无任何关系
     *                       那么这里的method就要通过 getMethod(String name, Class<?>... parameterTypes)
     *                       2：如getProxyWay002 这里代理中介类本身就是它的实例,所以可以直接method就是他本身即可执行
     *
     * @param taget 要代理的真实对象
     *
     * @param <T>
     * @return        动态产生一个代理类
     * @throws Exception
     */
    public static <T> T getProxyWay001(Class<T> interfaceClass, Object taget) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();//它能够获得这个对象所实现的所有接口(如果一个class有多个实现接口，那么数组长度就是实现接口数)
        Object o = Proxy.newProxyInstance(
                classLoader,/*类加载器*/
                classes,/*让代理对象和目标对象实现相同接口*/
                new JDKInvocationHandler(taget)
//                new InvocationHandler() {/*代理对象的方法最终都会被JVM导向它的invoke方法*/
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        Method finalMethod = taget.getClass().getMethod(method.getName(), ReflectionUtils.getParameterTypes(args));
//                        System.out.println(method.getName() + "方法开始执行...");
//                        Object result = finalMethod.invoke(taget, args);
//                        System.out.println("代理反射执行结果"+result);
//                        System.out.println(method.getName() + "方法执行结束...");
//                        return result;
//                    }
//                }
        );
        return  (T)o;
    }


    /**
     * 用代理真实对象的接口来当做中介类实例 (这里的method可以直接 invoke)
     *
     * @param taget  代理真实对象
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends IHello> T getProxyWay002(Object taget) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = taget.getClass().getInterfaces();//它能够获得这个对象所实现的所有接口(如果一个class有多个实现接口，那么数组长度就是实现接口数)
        Object o = Proxy.newProxyInstance(
                classLoader,/*类加载器*/
                classes,/*让代理对象和目标对象实现相同接口*/
                new InvocationHandler() {/*代理对象的方法最终都会被JVM导向它的invoke方法*/
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        Method method1 = taget.getClass().getMethod(method.getName(), ReflectionUtils.getParameterTypes(args));
//                        System.out.println(method.getName() + "方法开始执行...");
//                        Object result = method1.invoke(taget, args);
//                        System.out.println(result);
//                        System.out.println(method.getName() + "方法执行结束...");
//                        return result;
                        System.out.println(method.getName() + "方法开始执行...");
                        Object result =  method.invoke(taget,args);
                        System.out.println(method.getName() + "方法执行结束...");
                        return  result;
                    }
                }
        );
        return  (T)o;
    }

}
