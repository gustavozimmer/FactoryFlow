package com.zimmer.FactoryFlow.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationJwt;


    public String generateToken(UserDetails userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Date now = new Date();
        Date expiration = Date.from(Instant.now().plus(Duration.ofMinutes(expirationJwt)));
        String jwt = Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
        return jwt;

    }

}
