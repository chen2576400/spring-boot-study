package com.chenning.springbootlearn.netty.nettyServer.factory;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * ChannelInboundHandler：处理入站事件
 * ChannelOutboundHandler：处理出站事件
 *对于服务端来说，进入服务端的数据就是入站数据，由服务端向外发出的数据就是出站。
 *
 * 对于客户端来说，来自服务端的数据就是入站，向服务端发送的数据就是出站
 *
 * @Author nchen
 * @Date 2021/10/11 15:41
 * @Version 1.0
 * @Description  (拦截出站动作)表示出去的动作，监听自己的IO操作，比如connect，bind等，在重写这个Adapter的方法时，记得执行super.xxxx，否则动作无法执行。
 */
public class MsgOutboundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            wsWrite(ctx, msg, promise);
    }

    private void wsWrite(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        if(msg instanceof String){
            ctx.write(new TextWebSocketFrame((String) msg), promise);
            return;
        }
        if (msg instanceof byte[]) {
            ctx.write(new BinaryWebSocketFrame(Unpooled.wrappedBuffer((byte[]) msg)), promise);
            return;
        }
        ctx.write(msg, promise);
    }
}
