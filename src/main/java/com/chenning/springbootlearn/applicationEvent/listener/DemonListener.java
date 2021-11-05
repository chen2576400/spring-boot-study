package com.chenning.springbootlearn.applicationEvent.listener;

import com.chenning.springbootlearn.applicationEvent.event.DemonRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author nchen
 * @Date 2021/7/5 15:21
 * @Version 1.0
 * @Description
 */
@Component
public class DemonListener implements ApplicationListener<DemonRegisterEvent> {

    @Override
    //@Async  //同步会阻塞等待结果
    public void onApplicationEvent(DemonRegisterEvent event) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object source = event.getSource();
        String eventName = event.getEventName();
        System.out.println("当前线程"+Thread.currentThread().getName()+"接收到的key=====》"+eventName+"接收到的value======》"+source);

    }
}
