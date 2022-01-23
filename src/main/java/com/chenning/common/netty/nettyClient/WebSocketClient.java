package com.chenning.common.netty.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author nchen
 * @Date 2021/10/12 17:23
 * @Version 1.0
 * @Description  单例启动 二选一
 */
public class WebSocketClient {
    private EventLoopGroup workerGroup;
    private Bootstrap serverBootstrap;
    private ChannelFuture future;

    private static class SingletonWebSocketServer{
        static final WebSocketClient instance=new WebSocketClient();
    }

    public static WebSocketClient getInstance(){
        return SingletonWebSocketServer.instance;
    }

    private WebSocketClient(){
        workerGroup = new NioEventLoopGroup();
        try {
            serverBootstrap = new Bootstrap();
            serverBootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new WebSocketClientInitialzer());
        }catch (Exception e){

        }
    }

    public void start() {
        try {
            this.future = serverBootstrap.connect("127.0.0.1",9011).sync();
            System.out.println("netty websocket server 启动完毕...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
