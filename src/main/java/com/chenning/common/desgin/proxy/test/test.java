package com.chenning.common.desgin.proxy.test;

import com.chenning.common.crud.model.User;
import com.chenning.common.desgin.proxy.JdkProxy;
import com.chenning.common.desgin.proxy.service.*;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-31 17:15
 **/
public class test {
    public static void main(String[] args) throws Exception {
               getWay001();
               getWay002();
    }

    public static void getWay001()throws Exception{
        //这里代理对象IProxy 和 Ihello并无任何继承关系 却可以通过代理模式通过 Iproxy去代理调用Ihello接口，
        //仔细观察使用代理之后 IProxy的真正实现类却没有执行，执行的是Ihello的
        IProxy hell01 = JdkProxy.getProxyWay001(ProxyImpl.class, new Hello01Impl());
        hell01.say("111");
        User user = new User();
        user.setUserId(11);
        hell01.dance(user);

        IProxy hell02 = JdkProxy.getProxyWay001(ProxyImpl.class, new Hello02Impl());
        hell02.say("222");
        user.setUserId(22);
        hell02.dance(user);
//      hell02.fight("111");代理的真实对象不存在该方法，执行异常
    }


    public static void getWay002()throws Exception{
        //这里才用的代理对象就是 Ihello本身，所以在代理接口里面反射直接method.invoke
        IHello proxyWay002 = JdkProxy.getProxyWay002(new Hello03Impl());
        proxyWay002.say("777");
        User user = new User();
        user.setUserId(33);
        proxyWay002.dance(user);
    }
}
