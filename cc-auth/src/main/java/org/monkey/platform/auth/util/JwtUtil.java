package org.monkey.platform.auth.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * JwtUtil
 *
 * @author cc
 * @since 2024/6/7 11:01
 */
@Slf4j
public class JwtUtil {
    private static final long expire_time = 1000L * 60 * 60; // 2秒是做测试用的（设置一个钟 1000 * 60 * 60 * 1）
    private static final String sign = "l89ep28axoy3trez1gqed2lk1gu849e0"; // 加密签名密钥

    /**
     * 创建token返回
     *
     * @param claims 用户信息
     * @return token
     */
    public static String createToken(Map<String, Object> claims) {
        // 创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder();
        // jwtToken -> abc.def.xyz
        return jwtBuilder
                // Header:头部
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // PayLoad:载荷（用户信息，就是需要保存的数据）
                .addClaims(claims)
                /*.claim("userId", "101")
                .claim("userName", "zhangsan")
                .claim("roleName", "admin")*/
                .setSubject("user-info") // 这个载荷的名称
                // Token的过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expire_time)) // 从当前系统时间往后存活一小时
                // id字段
                .setId(UUID.randomUUID().toString())
                // 设置加密算法和签名
                .signWith(SignatureAlgorithm.HS256, sign)
                // 使用”."连接成一个完整的字符串
                .compact();
    }

    /**
     * 获取解析的用户信息
     *
     * @param token token
     * @return Claims
     */
    public static Claims parserJwt(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }
        Jws<Claims> jws = Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        return jws.getBody(); // 存储的用户信息
    }

    /**
     * 获取解析的用户ID
     *
     * @param token token
     * @return String
     */
    public static String parserJwtById(String token) {
        if (token == null || token.trim().isEmpty()) {
            return "";
        }
        Jws<Claims> jws = Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        return (String) jws.getBody().get("userId"); // 存储的用户信息
    }

    /**
     * 校验token
     *
     * @param token token
     * @return true/false
     */
    public static boolean checkToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
        } catch (Exception e) {
            //log.error("token校验服务异常, ", e);
            throw e;
        }
        return true;
    }
}
