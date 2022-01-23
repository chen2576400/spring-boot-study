package com.chenning.springbootlearn.reflex.demon2.run;

import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.reflex.demon1.AgentEntity;
import com.chenning.springbootlearn.reflex.demon1.MethodUntil;
import com.chenning.springbootlearn.reflex.demon2.ReflectionUtils;
import com.chenning.springbootlearn.reflex.demon2.service.Command1000;
import com.chenning.springbootlearn.reflex.demon2.service.Command1001;
import com.chenning.springbootlearn.reflex.demon2.service.Command1002;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-19 17:20
 **/
public class Test2 {

    private static final String fullClassName = "com.chenning.springbootlearn.reflex.demon2.service.Command%s";

    @Test
    public void x() {
        Integer type = 1002;
        String clazzName = String.format(fullClassName, type);


        String s = "我是王毛";
        User user=new User();
        user.setPassword("666");
        user.setUserName("666");
        user.setAddress("666");



        Object o1 = ReflectionUtils.newInstance(clazzName, s);  //获取含参  new Command1001(s)
        //子类用父类接受
        Command1000 o2 = (Command1000)ReflectionUtils.newInstance(clazzName);   //获取无参   new Command1001()
        Command1002 o3 = (Command1002)ReflectionUtils.newInstance(clazzName,user,s);   //获取无参   new Command1001(User user,String str)


        //父类方法是可以用子类查询(子类查不到往上父类再查)
        Method method = MethodUntil.getMethod(o1.getClass(), "say1000",String.class);//获取父类方法
        //既然是调用父类方法，所以这里的类可以写父类 也可以写子类（new  Command1000 或者  Command1002）
        AgentEntity agentEntity = AgentEntity.builder().method(method).object(o2).args(new Object[]{"方法字符串参数"}).build();
        try {
            Object invoke = agentEntity.getMethod().invoke(agentEntity.getObject(), agentEntity.getArgs()); //执行
            System.out.println("方法返回值+"+invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void xx() {
        Integer type = 1002;
        String clazzName = String.format(fullClassName, type);


        String s = "我是王毛";
        User user=new User();
        user.setPassword("666");
        user.setUserName("666");
        user.setAddress("666");



        Object o1 = ReflectionUtils.newInstance(clazzName, s);  //获取含参  new Command1001(s)
        //子类用父类接受(O2  其实还是 command1002)
        Command1000 o2 = (Command1000)ReflectionUtils.newInstance(clazzName);   //获取无参   new Command1001()
        Command1002 o3 = (Command1002)ReflectionUtils.newInstance(clazzName,user,s);   //获取无参   new Command1001(User user,String str)


        //这里采取调用子类方法（就必须用子类来接受    o1  o2  o3 都可以）
        Method method = MethodUntil.getMethod(o1.getClass(), "say1002",String.class);
        AgentEntity agentEntity = AgentEntity.builder().method(method).object(o1).args(new Object[]{"方法字符串参数"}).build();
        try {
            Object invoke = agentEntity.getMethod().invoke(agentEntity.getObject(), agentEntity.getArgs()); //执行
            System.out.println("方法返回值+"+invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
