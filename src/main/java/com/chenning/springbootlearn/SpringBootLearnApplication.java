package com.chenning.springbootlearn;


import com.chenning.springbootlearn.netty.nettyServer.server.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mapper的扫描路径不完整导致org.apache.ibatis.binding.BindingException
 * https://blog.csdn.net/wzg725/article/details/79317394?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chenning.springbootlearn.*.mapper")
public class SpringBootLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnApplication.class, args);

        //try {
        //    String host = "127.0.0.1";
        //    int port = 12345;
        //    NettyServer server = new NettyServer(port);
        //    server.run();
        //    //Thread.sleep(1000);
        //    //NettyClient client = new NettyClient(host, port);
        //    //client.connect();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        try {//和网页端web
            String host = "127.0.0.1";
            int port = 9011;
            WebSocketServer server = new WebSocketServer(port);
            server.start();





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
