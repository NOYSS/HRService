package com.noyss.hr.configs.security.jwt;

import com.noyss.hr.service.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
 
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
 
    @Value("${app.jwt.secret}")
    private String jwtSecret;
 
    @Value("${app.jwt.expiration}")
    private int jwtExpiration = 86400000;
 
    public String generateJwtToken(Authentication authentication) {
 
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        Key SIGN_KEY = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS256.getJcaName());
        String JWT =  Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SIGN_KEY)
                .compact();
        return JWT;
    }
 
    public String getUserNameFromJwtToken(String token) {
        Key SIGN_KEY = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parser()
                      .setSigningKey(SIGN_KEY)
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
 
    public boolean validateJwtToken(String authToken) {
        Key SIGN_KEY = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS256.getJcaName());
        try {
            Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
}
