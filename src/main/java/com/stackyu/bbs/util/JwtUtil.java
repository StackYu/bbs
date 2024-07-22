package com.stackyu.bbs.util;

import com.stackyu.bbs.pojo.bean.Payload;
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
        return generateTokenExpireInSeconds(userInfo, privateKey, expire);
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
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJVU0VSX0lORk8iOiJ7XCJpZFwiOjQsXCJjcmVhdGVUaW1lXCI6MTcyMDI4MzQwNDAwMCxcInVwZGF0ZVRpbWVcIjoxNzIwMjgzNDA0MDAwLFwic3RhdHVzXCI6MCxcIm9yZGVyXCI6MCxcInZlcnNpb25cIjowLFwiZGVsZXRlXCI6ZmFsc2UsXCJ1c2VyTmFtZVwiOlwiZmlzaFwiLFwicGFzc3dvcmRcIjpcIjU2MWI4NDZhY2E0ZGRlYWE0ZTQ4NjNjODY5YzkyZTZlXCIsXCJ1aWRcIjpcIjI5MTM1NTgxMzQ4MDYzMjMyMFwifSIsImp0aSI6IlpEUmtNamczWldFdFlXUTJOeTAwT1RnMkxXRmlORGt0T1dNMU1UWTJOR0UwWXpVeSIsImV4cCI6MTcyMTYzODE0OX0.avCHRbS-tzE6XgD_jcofR6wJO9fwhCSXYhoD2QhVIc6oC-tXvx2E_Q8pHEyhZuodIk2W_HLtmU7euN-SDuabbPRJqUi1WPlsA7rhPv99gV55vfSeYG9KVWgACXYzLm-U5B8U0p5uk13TJbIcKVoBzpS5_0Ovpupnv2_e2wiIjOneQxnf1tEV4yVeJdfnpSCC5Oga9WvoKmZ54uWJmHMjPNSvKO2ZEy1zBiIAgKk6X4BFljP8tg-42_pJwwczyMEjD3zoyKTS-XZQJEUJaQO8OI6UaBIw01h69Dss7YoaVc2X6kwevG-NMf8zh8B95kVFU3u5150FHA-mtrYxX6Rl8Q";
        /*String x = "sasasasasasasasas";
        String string = generateTokenExpireInSeconds(x, RsaUtil.getPrivateKey("/Users/yujiangzhong/IdeaProjects/Java/id_rsa"), 30);
        System.out.println(string);*/
        PublicKey publicKey = RsaUtil.getPublicKey("/Users/yujiangzhong/IdeaProjects/Java/id_rsa.pub");
        Payload<String> infoFromToken = getInfoFromToken(token, publicKey, String.class);
        System.out.println(infoFromToken.getUserInfo());
    }
}
