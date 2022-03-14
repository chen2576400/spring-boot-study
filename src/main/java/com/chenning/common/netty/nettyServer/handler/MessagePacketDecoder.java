//package com.chenning.common.netty.nettyServer.factory;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.util.List;
//
///**
// * @Author nchen
// * @Date 2021/10/13 14:32
// * @Version 1.0
// * @Description
// */
//public class MessagePacketDecoder extends ByteToMessageDecoder {
//    @Override
//    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buffer, List<Object> out) throws Exception {
//        try {
//            if (buffer.readableBytes() > 0) {
//                // 待处理的消息包
//                byte[] bytesReady = new byte[buffer.readableBytes()];
//                buffer.readBytes(bytesReady);
//                //进行具体的解码处理
//                System.out.println("解码器收到数据："+ByteUtils.toHexString(bytesReady));
//                //这里不做过多处理直接把收到的消息放入链表中，并向后传递
//                out.add(bytesReady);
//
//            }
//        }catch(Exception ex) {
//
//        }
//    }
//}
