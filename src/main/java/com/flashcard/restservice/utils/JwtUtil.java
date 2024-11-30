package com.flashcard.restservice.utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil implements IJwtUtil{
    private final int jwtExpirationMs = Integer.parseInt(System.getProperty("EXPIRATION_TIME")); // 1 hour
    private final SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(System.getProperty("JWT_SECRET")));

    private Date calculateExpirationDate(){
        Instant expirationTime = Instant.now().plus(Duration.ofMillis(jwtExpirationMs));
        return Date.from(expirationTime);
    }
    public String generateJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Date expirationDate = calculateExpirationDate();
        claims.put("userName", username);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    @Override
    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }
    @Override
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
