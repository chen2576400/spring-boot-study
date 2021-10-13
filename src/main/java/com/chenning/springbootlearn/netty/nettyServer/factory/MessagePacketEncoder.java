//package com.chenning.springbootlearn.netty.nettyServer.factory;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//
///**
// * @Author nchen
// * @Date 2021/10/13 14:30
// * @Version 1.0
// * @Description
// */
//public class MessagePacketEncoder extends MessageToByteEncoder<byte[]> {
//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, byte[] bytes, ByteBuf byteBuf) throws Exception {
//        //写入并传送数据
//        byteBuf.writeBytes(bytes);
//    }
//}
