package com.chenning.common.netty.nettyServer.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/10/12 10:29
 * @Version 1.0
 * @Description SimpleChannelInboundHandler 也是继承 ChannelInboundHandlerAdapter
 * channelRead0重写了里面的    channelRead方法
 * 如果其他类重写了ChannelInboundHandlerAdapter方法（比如心跳）  而且没做任何操作 并且在自定义handler之前pipeline.addLast(handler) 就无法进入此方法 默认进入先实现此接口的方法
 */
@Log4j2
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {
    //private TaskHandlerFactory taskHandlerFactory = new TaskHandlerFactory();

    /**
     * 该接口继承Set接口，因此可以通过ChannelGroup可管理服务器端所有的连接的Channel，然后对所有的连接Channel广播消息。(线程安全)
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数 by zhengkai.blog.csdn.net   new WebSocket("ws://127.0.0.1:12345/ws?uid=666&gid=777");
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap = getUrlParams(uri);
            System.out.println("接收到的参数是：" + JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }
        }
        else if(msg instanceof TextWebSocketFrame){
            //正常的TEXT消息类型
            TextWebSocketFrame frame=(TextWebSocketFrame)msg;
            System.out.println("客户端收到服务器数据：" +frame.text());

            //收到信息后，群发给所有channel
            channelGroup.writeAndFlush(new TextWebSocketFrame(frame.text()));
        }
        //super.channelRead(ctx, msg);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {



        //try {
        //    if (o instanceof TextWebSocketFrame){
        //        TextWebSocketFrame textWebSocketFrame=(TextWebSocketFrame)o;
        //        String content = textWebSocketFrame.text();
        //        if (StringUtils.isEmpty(content)) {
        //            return;
        //        }
        //        System.out.println("【服务端】收到消息"+content);
        //
        //
        //        String msg="你好客户端，我是服务器端";
        //        ctx.channel().writeAndFlush(new TextWebSocketFrame(msg));
        //    }
        //
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
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


    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }


}