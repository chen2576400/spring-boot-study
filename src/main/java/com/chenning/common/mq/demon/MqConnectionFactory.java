//package com.chenning.common.mq.demon;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
///**
// * @description:
// * @author: Mr.Nchen
// * @create: 2022-04-22 14:45
// **/
//@Configuration
//@Log4j2
//public class MqConnectionFactory {
//
//    //springboot 配置 默认为 beanName rabbitConnectionFactory 只存在一个ConnectionFactory实例时会自动注入RabbitTemplate，多个需要指定
//    //注意这个ConnectionFactory类是org.springframework.amqp.rabbit包下的类，而不是com.rabbit.client包下的类
//    @Bean(name = "rabbitConnectionFactory1")
//    @Primary
//    public ConnectionFactory connectionFactory1() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setHost("172.17.71.161");
//        connectionFactory.setPort(5672);
//        //设置通道缓存最大值
//        connectionFactory.setChannelCacheSize(50);
//        //设置缓存模式
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//        //设置最大连接数
//        connectionFactory.setConnectionLimit(50);
//        return  connectionFactory;
//    }
//
//
//    @Bean(name = "rabbitConnectionFactory2")
//    //注意这个ConnectionFactory类是org.springframework.amqp.rabbit包下的类，而不是com.rabbit.client包下的类
//    public ConnectionFactory connectionFactory2() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setHost("localhost");
//        connectionFactory.setPort(5672);
//        //设置通道缓存最大值
//        connectionFactory.setChannelCacheSize(50);
//        //设置缓存模式
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//        //设置最大连接数
//        connectionFactory.setConnectionLimit(50);
//        return  connectionFactory;
//    }
//
//
//    /**
//     * 没有必须是prototype类型，rabbitTemplate是thread safe的，主要是channel不能共用，但是在rabbitTemplate源码里channel是threadlocal的，所以singleton没问题。但是rabbitTemplate要设置回调类，如果是singleton，回调类就只能有一个，所以如果想要设置不同的回调类，就要设置为prototype的scope。
//     * @param connectionFactory
//     * @return
//     */
//    @Bean(name = "rabbitTemplate1")
////    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//必须是prototype类型
//    public RabbitTemplate normalRabbitTemplate(
//            @Qualifier("rabbitConnectionFactory1") ConnectionFactory connectionFactory
//    ) {
//        RabbitTemplate  rabbitTemplate1= new RabbitTemplate(connectionFactory);
////        rabbitTemplate1.setConfirmCallback((correlationData, ack, cause) ->
////        {
////            if (!ack) {
////                log.error("send message failed: " + cause + correlationData.toString());
////            }else {
////                log.info("消息发送成功"+  cause  +  correlationData.toString());
////            }
////        });
//        return rabbitTemplate1;
//    }
//
//
//    @Bean(name = "rabbitTemplate2")
////    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//必须是prototype类型
//    public RabbitTemplate normalRabbitTemplate2(
//            @Qualifier("rabbitConnectionFactory2") ConnectionFactory connectionFactory
//    ) {
//        RabbitTemplate normalRabbitTemplate = new RabbitTemplate(connectionFactory);
//        return normalRabbitTemplate;
//    }
//
//}
