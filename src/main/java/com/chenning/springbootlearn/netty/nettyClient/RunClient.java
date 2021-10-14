package com.chenning.springbootlearn.netty.nettyClient;

/**
 * @Author nchen
 * @Date 2021/10/13 15:16
 * @Version 1.0
 * @Description
 */
public class RunClient {
    public static void main(String[] args) {
        WebSocketClient.getInstance().start();
    }
}
