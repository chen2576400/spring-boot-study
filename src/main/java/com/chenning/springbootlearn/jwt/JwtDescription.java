package com.chenning.springbootlearn.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chenning.springbootlearn.util.time.DateUtils;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author nchen
 * @Date 2021/9/29 15:10
 * @Version 1.0
 * @Description
 */
public class JwtDescription {
    {
        /**
         * jwt由三部分组成{header  payload signature}
         *
         *
         * 头部  {"typ":"JWT","alg":"HS256"} 默认加密方式
         * 使用base64加密  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
         *
         *
         * 载荷 {"sub":"1234567890","name":"John Doe","admin":true}
         * 使用base64加密 eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9
         *
         *
         * 签名 由头部信息使用base64加密之后,拼接上载荷使用base64加密之后的部分,在加上当前的密钥,进行头部中的加密算法进行加密
         * header (base64后的)
         * payload (base64后的)
         * secret  自定义密钥
         * 这个部分需要base64加密后的header和base64加密后的payload使用.连接组成的字符串，然后通过header中声明的加密方式进行加盐secret组合加密，然后就构成了jwt的第三部分。
         * TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
         *
         * 将这三部分用.连接成一个完整的字符串,构成了最终的jwt:
         * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
         */
    }


    /**
     * 生成JWT-token
     */
    @Test
    public void test1() {
        Map<String, Object> headerClaims = new HashMap<>();
        String token = JWT.create().withJWTId(UUID.randomUUID().toString()) // 设置JWT的ID
                .withHeader(headerClaims) // 设置头部信息(header)
                .withIssuer("nchen") // 签名的生成者
                .withClaim("userId", 1001)  //(payload)
                .withClaim("userName", "张三")//(payload)
                .withSubject("SUBJECT") // 签名主题
                .withNotBefore(new Date()) // 定义在什么时间之前,该JWT都是不可用的
                .withAudience("AUDIENCE") // 签名的接受者
                .withIssuedAt(new Date()) // 生成签名的时间
                .withExpiresAt(org.apache.commons.lang3.time.DateUtils.addMinutes(new Date(), 5))// 签名过期时间(当前时间+1分钟)
                .sign(Algorithm.HMAC256("ownmiyao"));//签名(signature)     设置的密钥保密复杂
        System.out.println(token);
    }



    @Test
    public  void test2(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTVUJKRUNUIiwiYXVkIjoiQVVESUVOQ0UiLCJuYmYiOjE2MzI5MDM3OTksImlzcyI6Im5jaGVuIiwidXNlck5hbWUiOiLlvKDkuIkiLCJleHAiOjE2MzI5MDQwOTksInVzZXJJZCI6MTAwMSwiaWF0IjoxNjMyOTAzNzk5LCJqdGkiOiJkYTJhM2UyZi1kZDlmLTQ4OGUtYWQ5OS0yYTk3NWM0ZmUwYzgifQ.G0cHjIL4Hk7A06s3zD-qFXc7l0Pg6J_bAocgPoiWP0U";
        //创建验证对象(先验证签名)
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ownmiyao")).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        Map<String, Claim> claims = verify.getClaims();
        Integer userId = claims.get("userId").asInt();
        String userName = claims.get("userName").asString();
        String date = DateUtils.getDateToString(verify.getExpiresAt().getTime(), null);
        System.out.println(userId+"============"+userName+"过期时间为"+date);
    }
}
