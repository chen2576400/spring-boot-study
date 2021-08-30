package com.chenning.springbootlearn.mybatisPlus.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

/**
 * @Author nchen
 * @Date 2021/7/5 17:12
 * @Version 1.0
 * @Description
 * RestTemplate支持通过setRequestFactory设置HTTP请求客户端工具，支持jdk、httpclient、okHttp等，
 *      * 默认使用的是SimpleClientHttpRequestFactory，
 * 这里要自定义factory
 *
 */
public class HttpComponentsClientRestfulHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
    @Override
    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {

        if (httpMethod == HttpMethod.GET) {
            return new HttpGetRequestWithEntity(uri);
        }
        return super.createHttpUriRequest(httpMethod, uri);
    }

    /**
     * 定义HttpGetRequestWithEntity实现HttpEntityEnclosingRequestBase抽象类，以支持GET请求携带body数据
     */

    private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
        public HttpGetRequestWithEntity(final URI uri) {
            super.setURI(uri);
        }

        @Override
        public String getMethod() {
            return HttpMethod.GET.name();

        }
    }
}