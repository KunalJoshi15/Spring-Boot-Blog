package com.springboot.blog.security;

import com.springboot.blog.exception.BlogApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("app.jwt-expiration-milliseconds")
    private int jwtExpirationInMs;

    // generate token
    public String generateToken(Authentication authentication){
        String name = authentication.getName();
        Date current_date = new Date();
        Date expireDate = new Date(current_date.getTime()+jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(name)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();

        return token;
    }

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    // validate JWT token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch(SignatureException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Invalid JWT Signature");
        }
        catch(MalformedJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Invalid JWT Token");
        }
        catch(ExpiredJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Expired JWT token");
        }
        catch(UnsupportedJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Unsupported JWT Token");
        }
        catch(IllegalArgumentException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Jwt claims string is empty");
        }
    }
}
