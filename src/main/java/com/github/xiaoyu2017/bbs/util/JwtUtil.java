package com.github.xiaoyu2017.bbs.util;

import com.github.xiaoyu2017.bbs.pojo.bean.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class JwtUtil {
    private static final String JWT_PAYLOAD_USER_KEY = "USER_INFO";

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位分钟
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey, int expire) {
        return generateTokenExpireInSeconds(userInfo, privateKey, expire * 60);
    }

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位秒
     * @return JWT
     */
    public static String generateTokenExpireInSeconds(Object userInfo, PrivateKey privateKey, int expire) {
        if (userInfo instanceof String) {
            return Jwts.builder()
                    .claim(JWT_PAYLOAD_USER_KEY, userInfo)
                    .setId(createJTI())
                    .setExpiration(dateUtil(expire))
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        } else {
            return Jwts.builder()
                    .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                    .setId(createJTI())
                    .setExpiration(dateUtil(expire))
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        }
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        String typeName = userType.getTypeName();
        typeName = typeName.substring(typeName.lastIndexOf('.') + 1);
        if ("String".equals(typeName)) {
            claims.setUserInfo((T) body.get(JWT_PAYLOAD_USER_KEY).toString());
        } else {
            claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        }
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    private static Date dateUtil(int expire) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(expire);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) throws Exception {
        String x = "sasasasasasasasas";
        String string = generateTokenExpireInSeconds(x, RsaUtil.getPrivateKey("/Users/yujiangzhong/IdeaProjects/Java/id_rsa"), 30);

        PublicKey publicKey = RsaUtil.getPublicKey("/Users/yujiangzhong/IdeaProjects/Java/id_rsa.pub");
        Payload<String> infoFromToken = getInfoFromToken("eyJhbGciOiJSUzI1NiJ9.eyJVU0VSX0lORk8iOiI1NjFiODQ2YWNhNGRkZWFhNGU0ODYzYzg2OWM5MmU2ZSIsImp0aSI6IlltSmhORFU1TURrdFpXRmhNeTAwT0RObExXSTJaV010WmpobE4ySmpZakUwT1RrNSIsImV4cCI6MTcyMTQxMTk1Nn0.kbBM3Mf6REOyDEdyYkwe2WR9UqNYjeW9BIhvQ_N68Lkms3HGCfCCpqZ_msdw37o4PUKNYC0V8ROi28qU9cABLbxJxWgukPp1aVX6lUsrDCJoiak4fBpIXmctsByo0UdV8sJBRoaLaEwig8Y883ssHF3PS6bv7ZKmUH8fuJpGbds4DpUlvwco5F2dbCGLaZg0CSBHjIW8BCM9FpZz6Sr_Q0QWs3XVdPj0za1LEKdRGD87kQo5w__BMQusKFikFw4CMvMG0DcPDzsuKcF7n6rIRbE8ufVcslhc-caeIgzzvvoTYIdik-uiKn3xMZJuOTXc1v1wCJe4L7wcIiGCMB_Ivw", publicKey, String.class);
        System.out.println(infoFromToken.getUserInfo());
    }
}
