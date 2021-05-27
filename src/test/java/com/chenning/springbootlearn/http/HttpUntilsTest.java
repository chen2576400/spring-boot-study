package com.chenning.springbootlearn.http;

import com.alibaba.fastjson.JSON;
import com.chenning.springbootlearn.SpringBootLearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/5/27 13:50
 * @Version 1.0
 * @Description
 */
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class HttpUntilsTest {


    /**
     * 工具类  HttpRequest调用
     * 参数为地址挂参
     */
    @Test
    public void doGet() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("s", 4);

        HttpRequest httpRequest = HttpRequest.get("http://localhost:6666/user/findAllUserByID", params)
                .trustAllCerts()
                .trustAllHosts()
                .accept("application/json, text/plain, */*")
                .contentType("application/json", HttpRequest.CHARSET_UTF8)
                .readTimeout(30 * 1000)
                .connectTimeout(30 * 100);
        String response = httpRequest.getResponse();
        System.out.println(response);
    }



    /**
     * 工具类  HttpRequest调用
     * 参数为json  也可以追加地址挂参 都可以  区别就是 .send()
     */
    @Test
    public void doGet1() {
        HttpRequest httpRequest = HttpRequest.get("http://localhost:6666/user/findUserAndCardByID1")
                .send(JSON.toJSONString(1))  //要传入的json 参数
                .trustAllCerts()
                .trustAllHosts()
                .accept("application/json, text/plain, */*")
                .contentType("application/json", HttpRequest.CHARSET_UTF8)
                .readTimeout(30 * 1000)
                .connectTimeout(30 * 100);

        String response = httpRequest.getResponse();
        System.out.println(response);
    }


    /**
     * 工具类 HttpUtils 调用json  或者 地址挂参合一
     * 参数为json
     */
    @Test
    public void doPost() {
        String s = JSON.toJSONString(1);
        String response = HttpUtils.doPost("http://localhost:6666/user/findUserAndCardByID1", null, s);
        System.out.println(response);
    }


//    private Map<String, Object> getParams() {
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 1);
//        params.put("s", 4);
//        return params;
//    }

}
