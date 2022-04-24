package com.chenning.common.mq.producer;

import com.chenning.common.crud.model.User;
import com.chenning.common.mq.constant.Constant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 14:57
 **/
@Service
public class SenderOne {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sender(List<User> list) {
        this.rabbitTemplate.convertAndSend("Direct", Constant.QUEUE_ONE, list);
    }


    public void  senderBytest(String test){
        this.rabbitTemplate.convertAndSend("Direct", Constant.QUEUE_TWO, test);
    }
}
