package com.chenning.springbootlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author nchen
 * @Date 2021/9/29 17:27
 * @Version 1.0
 * @Description
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")  //拦截接口
                .excludePathPatterns("/jwt/login");//放行接口
    }


    @Bean
    JwtInterceptor jwtInterceptor() { //注入到容器 这样在拦截器里面可以注入
        return new JwtInterceptor();
    }

}
