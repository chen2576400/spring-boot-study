package com.chenning.common.mq.runTest;

import com.chenning.common.SpringBootLearnApplication;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import com.chenning.common.mq.producer.SenderOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 15:05
 **/
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class QueueTest {

    @Autowired
    private SenderOne sender;
    @Autowired
    private UserService userService;

    @Test
    public void sendMessage() {
        List<User> allUser = userService.findAllUser();
        sender.sender(allUser);
    }

    @Test
    public void sendMessageBytext() {
        sender.senderBytest("你好，我是派睿究极打工仔");
    }
}
