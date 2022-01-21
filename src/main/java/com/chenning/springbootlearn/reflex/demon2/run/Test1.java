package com.chenning.springbootlearn.reflex.demon2.run;

import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.quartz.config.SpringContextUtils;
import com.chenning.springbootlearn.reflex.demon1.AgentEntity;
import com.chenning.springbootlearn.reflex.demon1.MethodUntil;
import com.chenning.springbootlearn.reflex.demon1.service.BaseService;
import com.chenning.springbootlearn.reflex.demon1.service.DanceService;
import com.chenning.springbootlearn.reflex.demon2.ReflectionUtils;
import com.chenning.springbootlearn.reflex.demon2.service.Command1001;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-19 17:20
 **/
public class Test1 {

    private static final String fullClassName = "com.chenning.springbootlearn.reflex.demon2.service.Command%s";

    @Test
    public void x() {
        Integer type = 1001;
        String clazzName = String.format(fullClassName, type);


        String s = "我是王毛";
        User user=new User();
        user.setPassword("666");
        user.setUserName("666");
        user.setAddress("666");



        Object o1 = ReflectionUtils.newInstance(clazzName, s);  //获取含参  new Command1001(s)
        Object o2 = (Command1001)ReflectionUtils.newInstance(clazzName);   //获取无参   new Command1001()
        Object o3 = (Command1001)ReflectionUtils.newInstance(clazzName,user,s);   //获取无参   new Command1001()


        Method method = MethodUntil.getMethod(o1.getClass(), "say1001",String.class);//获取类方法
        AgentEntity agentEntity = AgentEntity.builder().method(method).object(o1).args(new Object[]{"方法字符串参数"}).build();
        try {
            Object invoke = agentEntity.getMethod().invoke(agentEntity.getObject(), agentEntity.getArgs()); //执行
            System.out.println("方法返回值+"+invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
