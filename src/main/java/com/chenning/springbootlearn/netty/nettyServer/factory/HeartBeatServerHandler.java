package com.chenning.springbootlearn.netty.nettyServer.factory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author nchen
 * @Date 2021/10/11 18:06
 * @Version 1.0
 * @Description 心跳控制
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HeartBeatServerHandler.class);
    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                lossConnectCount++;
                if (lossConnectCount > 2) {
                    logger.info("close when non-heart-beating:{}", ctx.channel().toString());
                    ctx.channel().close();
                }
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("进入写空闲……");
            } else if (event.state() == IdleState.ALL_IDLE) {
                /*总超时*/
                System.out.println("===服务端===(ALL_IDLE 总超时)");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    //@Override     //这里如果重写了方法并且在自定义handler之前pipeline.addLast     会导致自定义handler重写的方法失效不进入，默认在这里输出
    //public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //    lossConnectCount = 0;
    //}
    //
    //@Override
    //public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    //    ctx.close();
    //}

}
