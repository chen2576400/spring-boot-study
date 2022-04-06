package com.chenning.common.swagger.config;

import com.chenning.common.jwt.config.LoginConstant;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;
import java.util.*;
/**
 * @description: swagger配置
 * @author: Mr.Nchen
 * @create: 2022-03-23 10:53
 * http://localhost:1025/swagger-ui/index.html
 **/
@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                // 将api的元信息设置为包含在json ResourceListing响应中。
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))
                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())
                // 授权信息全局应用
                .securityContexts(securityContexts());
//                //全局通用参数     每个方法上都会加上一个请求头参数以供使用
//                .globalRequestParameters(getGlobalRequestParameters());
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("客户管理中心 API 1.0 操作文档")
                .contact(new Contact("重汽项目。", "http://localhost:9998/login", "ruiyeclub@foxmail.com"))
                .version("1.0")
                .build();
    }


    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey(LoginConstant.HEADER_TOKEN, LoginConstant.HEADER_TOKEN, In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     * LoginConstant.HEADER_TOKEN 是请求头parm
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference(LoginConstant.HEADER_TOKEN, new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }



    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name(LoginConstant.HEADER_TOKEN)
                .description("用户token")
                .required(true)
                .in(ParameterType.HEADER)  //请求头部
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
//        parameters.add(new RequestParameterBuilder()
//                .name("udid")
//                .description("设备的唯一id")
//                .required(true)
//                .in(ParameterType.QUERY)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(false)
//                .build());
//        parameters.add(new RequestParameterBuilder()
//                .name("version")
//                .description("客户端的版本号")
//                .required(true)
//                .in(ParameterType.QUERY)
//                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                .required(false)
//                .build());
        return parameters;
    }







    /**
     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration
                            .excludePathPatterns("/swagger**/**")
                            .excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**")
                            .excludePathPatterns("/doc.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
