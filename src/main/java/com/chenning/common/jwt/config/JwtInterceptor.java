package com.chenning.common.jwt.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import com.chenning.common.jwt.JwtUtils;
import com.chenning.common.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author nchen
 * @Date 2021/9/29 16:54
 * @Version 1.0
 * @Description
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (1 == 1) {//登录默认放行
            return true;
        }

        String token = request.getHeader(LoginConstant.HEADER_TOKEN);
        // 获取token: 1. header 2.cookie
        if (StringUtils.isBlank(token) || "undefined".equals(token)) {
            Cookie cookie = WebUtils.getCookie(request, LoginConstant.HEADER_TOKEN);
            if (null != cookie) {
                token = cookie.getValue();
            }
        }

        // 没有获取到token
        if (StringUtils.isBlank(token) || "undefined".equals(token)) {
            unauthorized(response);
            return false;
        }


        //解析token
        Boolean verify = JwtUtils.verify(token);
        if (!verify) {
            unauthorized(response);
            return false;
        }


        String id;
        {//校验历史token （同一个用户其他地方登录会挤掉重新生成一个新的token）
            // 新的token 和之前已经登录过的前端放在请求头的token不一致，旧的登录页面再次刷新将强制踢出
            DecodedJWT decodedJWT = JwtUtils.getTokenInfo(token);
            id = decodedJWT.getClaim("id").asString();//获取用户ID
            String oldTotken = redisService.get(LoginConstant.ACCESS_TOKEN + id);//原来放在redis的用户token
            if (StringUtils.isNotEmpty(oldTotken) && !token.equals(oldTotken)) {
                unauthorized(response);
                return false;
            }
        }


        {//区分浏览器  (确保是同一个浏览器开启时不能重复登录不同用户)
           //同一个浏览器再次登录其他用户  以seesionId为key的redis 和之前已经登录过的前端放在请求头的token不一致，旧的其他用户页面再次刷新将强制踢出
            String sessionId = request.getSession().getId();//当前请求页面的sessionID
            String sessionToken = redisService.get(sessionId);//原来放在redis的session token
            if (StringUtils.isNotEmpty(sessionToken) && !sessionToken.equals(token)) {
                unauthorized(response);
                return false;
            }
        }


        User user = userService.selectById(id);

        // 未找到用户信息.
        if (null == user) {
            unauthorized(response);
            return false;
        }
        UserContext.set(user);


        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        UserContext.clear();
    }


    /**
     * 处理401.
     */
    private void unauthorized(HttpServletResponse response) throws IOException {
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=utf-8");
//        try {
//            writer = response.getWriter();
//            writer.write("{\"success\":false,\"code\":401,\"message\":\"没有登录.\"}");
//
//        } catch (IOException e) {
//        } finally {
//            if (writer != null) {
//                writer.close();
//            }
//        }
    }

    /**
     * 处理403.
     */
    private void forbidden(HttpServletResponse response) throws IOException {
//        //response.sendError(HttpServletResponse.SC_FORBIDDEN);// 403
//        response.getWriter()
//                .write("{\"success\":false,\"code\":403,\"message\":\"没有权限.\"}");
//        response.getWriter().close();
    }
}
