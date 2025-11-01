package com.example.utils;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtHelper {
  private long tokenExpiration;  // 有效时间，单位为秒
  private String tokenSignKey;  // 签名密钥

  // 生成 token 字符串
  public String createToken(Long userId) {
    // System.out.println("tokenExpiration = " + tokenExpiration);
    // System.out.println("tokenSignKey = " + tokenSignKey);
    return Jwts.builder()
      .setSubject("HEADLINE-USER")
      .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
      .claim("userId", userId)
      .signWith(SignatureAlgorithm.HS512, tokenSignKey)
      .compressWith(CompressionCodecs.GZIP)
      .compact();
  }

  // 从 token 字符串获取 userId
  public Long getUserId(String token) {
    if(StringUtils.isEmpty(token)) return null;
    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
    Claims claims = claimsJws.getBody();
    Integer userId = (Integer)claims.get("userId");
    return userId.longValue();
  }

  // 判断 token 是否有效
  public boolean isExpiration(String token) {
    try {
      // 没有过期，有效，返回 false
      return Jwts.parser()
        .setSigningKey(tokenSignKey)
        .parseClaimsJws(token)
        .getBody()
        .getExpiration().before(new Date());
    } catch (Exception e) {
      // 过期，出现异常，返回 true
      return true;
    }
  }
}
