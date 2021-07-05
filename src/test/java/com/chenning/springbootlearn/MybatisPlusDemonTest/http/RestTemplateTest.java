package com.chenning.springbootlearn.MybatisPlusDemonTest.http;

import com.alibaba.fastjson.JSON;
import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.demonBuild.model.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/5/27 15:50
 * @Version 1.0
 * @Description
 */

@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class RestTemplateTest {
    /**
     * 通用方法ForEntity ForObject  二合一======》 exchange
     *
     *
     * get方法 restTemplate不支持  设置jsonBody 如果要用要修改
     * RestTemplate支持通过setRequestFactory设置HTTP请求客户端工具，支持jdk、httpclient、okHttp等，
     * 默认使用的是SimpleClientHttpRequestFactory，
     * 该工程使用的JDK实现，底层使用OutputStream来传递body数据，不支持GET传递body。
     *
     *
     *
     * ForEntity() 可以拿到请求头信息  发送一个HTTP GET请求，返回的ResponseEntity包含了响应体所映射成的对象
     * ForObject()  无法拿到请求头信息 发送一个HTTP GET请求，返回的请求体将映射为一个对象
     */

    @Test
    public void doGet() {
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 1);
//        params.put("s", 4);
//        String url = HttpUtils.append("http://localhost:6666/user/findAllUserByID", params);
//         两种拼接地址挂参任选其一

        //拼接URL和参数
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:6666/user/findAllUserByID")
                .queryParam("id", 1)
                .queryParam("s", 4);

        String url = builder.toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        /**
         * url：请求路径
         * method：请求的方法（GET、POST、PUT等）
         * requestEntity：HttpEntity对象，封装了请求头和请求体 这个和responseType对应
         * responseType：返回数据类型
         * uriVariables：支持PathVariable类型的数据。
         */
        RestTemplate restTemplate = new RestTemplate();
/*        {  //返回json的字符串
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
            String body = exchange.getBody();
        }*/

        {  //直接返回list对象
            ParameterizedTypeReference<List<UserVo>> responseType = new ParameterizedTypeReference<List<UserVo>>() {
            };
            ResponseEntity<List<UserVo>> exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), responseType);
            List<UserVo> body = exchange.getBody();
            System.out.println(body);
        }

    }


    /**
     * 本身restTemplate不支持get请求jsonBody  通过设置factory来达到目的（需引入httpClient pom）
     */
    @Test
    public void doGet2() {
        //拼接URL和参数
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:6666/user/findUserAndCardByID2");
//                .queryParam("id", 1)
//                .queryParam("s", 4);

        String url = builder.toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());


        Map<String, Object> map = new HashMap<>(16);
        map.put("id",1);

        HttpEntity<String> httpEntity = new HttpEntity<String>(JSON.toJSONString(map),headers);

        /**
         * url：请求路径
         * method：请求的方法（GET、POST、PUT等）
         * requestEntity：HttpEntity对象，封装了请求头和请求体 这个和responseType对应
         * responseType：返回数据类型
         * uriVariables：支持PathVariable类型的数据。
         */

        HttpComponentsClientRestfulHttpRequestFactory httpRequestFactory = new HttpComponentsClientRestfulHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(60000);//单位毫秒
        httpRequestFactory.setConnectTimeout(60000);
        httpRequestFactory.setReadTimeout(60000);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(httpRequestFactory);//不设置的话默认的factory不支持get请求body


        {  //直接返回list对象
            ParameterizedTypeReference<List<UserVo>> responseType = new ParameterizedTypeReference<List<UserVo>>() {
            };
            ResponseEntity<List<UserVo>> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
            List<UserVo> body = exchange.getBody();
            System.out.println(body);
        }

    }
}
