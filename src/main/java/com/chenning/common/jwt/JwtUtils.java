package com.chenning.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/9/29 16:29
 * @Version 1.0
 * @Description
 */
public class JwtUtils {
    private static final String SING = "1QAZ@wsx";

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String creatToken(Map<String, String> map) {
        Date date = DateUtils.addMinutes(new Date(), 10); //默认10分钟过期

        //创建jwt Builder
        JWTCreator.Builder builder = JWT.create();
        builder.withIssuer("nchen") // 签名的生成者
                .withSubject("SUBJECT") // 签名主题
                .withNotBefore(new Date()) // 定义在什么时间之前,该JWT都是不可用的
                .withAudience("AUDIENCE") // 签名的接受者
                .withIssuedAt(new Date()); // 生成签名的时间
        map.forEach((k, v) -> { //payLoad
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(date)//过期时间
                .sign(getDefaultAlgorithm());//签名
        return  token;
    }


    /**
     * 验证token合法性
     * @param token
     */
    public static  Boolean verify(String token){
        try {
            JWT.require(getDefaultAlgorithm()).build().verify(token);
        } catch (JWTVerificationException e) {
           return false;
        }
        return true;
    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public static DecodedJWT  getTokenInfo(String token){
        DecodedJWT decodedJWT = JWT.require(getDefaultAlgorithm()).build().verify(token);
        return  decodedJWT;
    }





    public static Algorithm getDefaultAlgorithm() {
        return Algorithm.HMAC256(SING);
    }


}
