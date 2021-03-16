package com.chenning.springbootlearn.MybatisPlusDemonTest.reflex;

import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.demonBuild.model.User;
import com.chenning.springbootlearn.quartz.config.SpringContextUtils;
import com.chenning.springbootlearn.reflex.MethodUntil;
import com.chenning.springbootlearn.reflex.model.AgentEntity;
import com.chenning.springbootlearn.reflex.model.service.BaseService;
import com.chenning.springbootlearn.reflex.model.service.DanceService;
import com.chenning.springbootlearn.reflex.model.service.SingService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/15 14:41
 */
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class Test2 {
    @SneakyThrows
    @Test
   public  void  singTest(){
        String parm1="我是第一个参数";
        User parm2 = new User();
        parm2.setUserId(2);
        parm2.setUserName("二郎神");

        /**
         *  singService 和 danceService都  继承了 BaseService 所以反射时候可以用子类接收父类的方法
         */
        Method method = MethodUntil.getMethod(SingService.class, "doSomeThingBySing",String.class, User.class);
        AgentEntity agentEntity = AgentEntity.builder().method(method).object(SpringContextUtils.getBean(BaseService.class)).args(new Object[]{parm1, parm2}).build();
        agentEntity.getMethod().invoke(agentEntity.getObject(),agentEntity.getArgs());//直接执行该方法
    }





    @SneakyThrows
    @Test
    public  void  danceTest(){
        User parm2 = new User();
        parm2.setUserId(2);
        parm2.setUserName("二郎神");

        /**
         *  singService 和 danceService都  继承了 BaseService 所以反射时候可以用子类接收父类的方法
         */
        Method method = MethodUntil.getMethod(DanceService.class, "doSomeThingByDance",String.class, User.class);
        AgentEntity agentEntity = AgentEntity.builder().method(method).object(SpringContextUtils.getBean(BaseService.class)).args(new Object[]{parm2}).build();

//        super.add(agentEntity);  将需要反射执行的放入队列  每调用一次添加到队列里
    }


//    @PostConstruct
//    public void init() {   //项目启动从队列里取 进行反射调用
//        super.start();
//    }

}
