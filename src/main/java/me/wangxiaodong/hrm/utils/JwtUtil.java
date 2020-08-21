package me.wangxiaodong.hrm.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author: wangxiaodong
 * @create: 2020-03-08 10:24
 * @description: Jwt生成工具类
 **/
public class JwtUtil {


    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "wangxiaodonghrm";

    /**
     * 生成签名,15分钟后过期
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username,Long userId){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(String.valueOf(userId))
                .setSubject(username)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET);
        if (EXPIRE_TIME > 0) {
            builder.setExpiration(new Date(nowMillis + EXPIRE_TIME));
        }
        return builder.compact();
    }


    public static Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean checkExpireToken(String token){
        try {
            // jwt正常情况 则判断失效时间是否大于5分钟
            long expireTime = Jwts.parser()   //得到DefaultJwtParser
                    .setSigningKey(TOKEN_SECRET)  //设置签名的秘钥
                    .parseClaimsJws(token)
                    .getBody().getExpiration().getTime();
            long diff = expireTime - System.currentTimeMillis();
            //如果有效期小于1分钟，则不建议继续使用该token
            if (diff < 60 * 1000) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String token = sign("wangxiaodong",123456778L);
        Claims claims = parseJWT(token);
        String username = claims.getSubject();
        String userId = claims.getId();
        Date expiration = claims.getExpiration();
        System.out.println("userName:" + username + "  userId:" + userId + " date : " + expiration);
    }
}

