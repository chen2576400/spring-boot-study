package com.chenning.springbootlearn.netty.nettyServer.server;

import com.chenning.springbootlearn.netty.nettyServer.util.OSInfoMation;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;


/**
 * @Author nchen
 * @Date 2021/10/11 14:17
 * @Version 1.0
 * @Description
 */
@Log4j2
public class WebSocketServer implements  DisposableBean {
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workGroup = null;
    private ServerBootstrap bootstrap;
    private ChannelFuture future;
    private int mPort;


    public WebSocketServer(int port) {
        this.mPort = port;
    }

    @Override
    public void destroy() {
        this.shutdown();
    }

    public void start() {
        bootstrap = new ServerBootstrap();
        if (OSInfoMation.isLinux()) {
            bossGroup = new EpollEventLoopGroup(2, new DefaultThreadFactory("boss-thread", true));
            workGroup = new EpollEventLoopGroup(2, new DefaultThreadFactory("worker-thread", true));
            bootstrap.channel(EpollServerSocketChannel.class)
                    .group(bossGroup, workGroup)
                    .option(EpollChannelOption.TCP_CORK, true);
        } else {
            bossGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("boss-thread", true));
            workGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("worker-thread", true));
            bootstrap.channel(NioServerSocketChannel.class)
                    .group(bossGroup, workGroup);
        }
        //TCP协议中，TCP总是希望每次发送的数据足够大，避免网络中充满了小数据块。
        // Nagle算法就是为了尽可能的发送大数据快。
        // TCP_NODELAY就是控制是否启用Nagle算法。
        // 如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；
        // 如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
        bootstrap
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_BACKLOG, 1024)// BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                .handler(new LoggingHandler(LogLevel.INFO));
        try {
            bootstrap.childHandler(new WebSocketServerInitialzer());
            // 开始真正绑定端口进行监听
            future = bootstrap.bind(mPort).sync();
            log.info("netty服务器在[{}]端口启动监听", mPort);
            future.channel().closeFuture().sync();
            System.out.println("netty websocket server 启动完毕...");
        } catch (InterruptedException e) {
            log.info("[出现异常] 释放资源");
            shutdown();
        }
    }

    public void shutdown() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
