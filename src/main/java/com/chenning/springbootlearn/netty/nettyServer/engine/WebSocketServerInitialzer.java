package com.chenning.springbootlearn.netty.nettyServer.engine;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import com.chenning.springbootlearn.netty.nettyServer.factory.*;
/**
 * @Author nchen
 * @Date 2021/10/12 10:08
 * @Version 1.0
 * @Description
 */
public class WebSocketServerInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());// websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());//用于向客户端发送HTML5文件，用于支持游览器和服务端进行WebSocket通信(  // 以块的方式来写的处理器(添加对于读写大数据流的支持))
        pipeline.addLast(new HttpObjectAggregator(1024*1024));
        // websocket 服务器处理的协议，用于给指定的客户端进行连接访问的路由地址 (同时默认了消息载体)
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true, 1024 * 10 *1024));
        pipeline.addLast(new IdleStateHandler(60, 60, 120));
        pipeline.addLast(new HeartBeatServerHandler());//心跳机制事件触发
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(new MsgOutboundHandler());
        pipeline.addLast(new WebSocketHandler());//自定义handler（服务器处理客户端消息）

    }
}
