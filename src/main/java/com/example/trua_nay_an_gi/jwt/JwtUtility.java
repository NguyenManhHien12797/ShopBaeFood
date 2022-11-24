package com.example.trua_nay_an_gi.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtUtility implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);
    private String jwtSecret = "secrtkey";

    public String createJwTToken(String userName){
        return Jwts.builder()
//                truyền user name vào tocken
                .setSubject(userName)
//                thời gian khởi tạo token
                .setIssuedAt(new Date())
//                thời gian sống của token
                .setExpiration(new Date((new Date()).getTime()+ (100*60*60*24*30)))
//                Công nghệ mã hóa
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

//    giải mã token
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

//    in ra exception trong consolog
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
          logger.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("JWT token is expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
