package com.chenning.common.mq.customer;

import com.alibaba.fastjson.JSON;
import com.chenning.common.crud.model.User;
import com.chenning.common.mq.constant.Constant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 14:59
 **/
@Component
@RabbitListener(queues = Constant.QUEUE_ONE)
public class ReceiverOne {
    @RabbitHandler
    public void process(List<User> users, Channel channel, Message message) throws IOException {
        try {
            System.out.println("收到队列消息"+JSON.toJSONString(users));
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

    }
}
