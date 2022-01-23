package com.chenning.common.applicationEvent.runTest;

import com.chenning.common.SpringBootLearnApplication;
import com.chenning.common.applicationEvent.event.DemonRegisterEvent;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author nchen
 * @Date 2021/7/5 17:35
 * @Version 1.0
 * @Description
 */
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class EventTest {
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UserService userService;



    @Test
    public void send() throws Exception {
        StopWatch stopWatch=new StopWatch();
        List<User> allUser = userService.findAllUser();
        stopWatch.start();
        for (int i=0;i<10;i++){
            publisher.publishEvent(new DemonRegisterEvent("event队列"+i+1,allUser));
        }
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒.");
    }

}
