package com.chenning.springbootlearn.netty.nettyServer.factory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @Author nchen
 * @Date 2021/10/11 18:10
 * @Version 1.0
 * @Description
 */
public interface SocketMsgHandler<T> {

    void onConnect(ChannelHandlerContext ctx, HttpRequest req);

    void onMessage(ChannelHandlerContext ctx, T msg);

    void disConnect(ChannelHandlerContext ctx);

}
