//package com.chenning.common.mq.demon;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
///**
// * @description:
// * @author: Mr.Nchen
// * @create: 2022-04-22 14:56
// **/
//@Service
//public class SendMessage {
//    @Autowired
//    @Qualifier("rabbitTemplate1")  //三方mq
//    private RabbitTemplate rabbitTemplate1;
//
//    @Autowired
//    @Qualifier("rabbitTemplate2")
//    private RabbitTemplate rabbitTemplate2;
//
//
//
//
//
//    public  void  send(String str){
//        //交换机名称  ，队列名称  ，消息载体
//        this.rabbitTemplate1.convertAndSend("pisx.tundra.topic", "tundra.service.search", str);
//        this.rabbitTemplate2.convertAndSend("pisx.tundra.topic", "queue_fore", str);
//        this.rabbitTemplate2.convertAndSend("Direct", "queue_two", str);
//    }
//}
