package com.chenning.springbootlearn.netty.nettyServer.factory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author nchen
 * @Date 2021/10/12 10:29
 * @Version 1.0
 * @Description
 * SimpleChannelInboundHandler 也是继承 ChannelInboundHandlerAdapter
 * channelRead0重写了里面的    channelRead方法
 * 如果其他类重写了ChannelInboundHandlerAdapter方法（比如心跳）  而且没做任何操作 并且在自定义handler之前pipeline.addLast(handler) 就无法进入此方法 默认进入先实现此接口的方法
 */
@Log4j2
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {
    //private TaskHandlerFactory taskHandlerFactory = new TaskHandlerFactory();

    /**该接口继承Set接口，因此可以通过ChannelGroup可管理服务器端所有的连接的Channel，然后对所有的连接Channel广播消息。(线程安全)*/
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        try {
            if (o instanceof TextWebSocketFrame){
                TextWebSocketFrame textWebSocketFrame=(TextWebSocketFrame)o;
                String content = textWebSocketFrame.text();
                if (StringUtils.isEmpty(content)) {
                    return;
                }
                System.out.println("收到消息"+content);
                //WebSocketData webSocketData = JSON.parseObject(content, WebSocketData.class);
                //if (webSocketData.getVersion() == null || !webSocketData.getVersion().equals(WebSocketData.VERSION)) {
                //    logger.info("版本不一致，拒绝访问！");
                //    return;
                //}
                //TaskHandler taskHandler = taskHandlerFactory.getTaskHandlerInstance(webSocketData.getAction());
                //taskHandler.execute(ctx, webSocketData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * 覆盖了 handlerAdded() 事件处理方法。
     * 每当从服务端收到新的客户端连接时
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
    }

    /**
     * 覆盖了 handlerRemoved() 事件处理方法。
     * 每当从服务端收到客户端断开时
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
    }


    /**
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        channelGroup.remove(ctx.channel());
    }


}