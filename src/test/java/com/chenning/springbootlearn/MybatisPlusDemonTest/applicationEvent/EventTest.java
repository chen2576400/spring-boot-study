package com.chenning.springbootlearn.MybatisPlusDemonTest.applicationEvent;

import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.applicationEvent.event.DemonRegisterEvent;
import com.chenning.springbootlearn.demonBuild.model.User;
import com.chenning.springbootlearn.demonBuild.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<User> allUser = userService.findAllUser();
        publisher.publishEvent(new DemonRegisterEvent("event队列一",allUser));
    }

}
