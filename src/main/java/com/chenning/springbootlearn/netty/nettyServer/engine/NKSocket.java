package com.chenning.springbootlearn.netty.nettyServer.engine;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author nchen
 * @Date 2021/10/12 17:23
 * @Version 1.0
 * @Description  单例启动 二选一
 */
public class NKSocket {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture future;

    private static class SingletonWebSocketServer{
        static final NKSocket instance=new NKSocket();
    }

    public static NKSocket getInstance(){
        return SingletonWebSocketServer.instance;
    }

    private NKSocket(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketServerInitialzer());
        }catch (Exception e){

        }
    }

    public void start() {
        try {
            this.future = serverBootstrap.bind(9011).sync();
            future.channel().closeFuture().sync();
            System.out.println("netty websocket server 启动完毕...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
