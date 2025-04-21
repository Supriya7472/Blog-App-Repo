package com.example.Blog_App.security.jwt;

import com.example.Blog_App.config.AppEnv;
import com.example.Blog_App.exception.InvalidJWTException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor

public class JWTService {
    private final AppEnv env;

    public String generateToken(TokenPayload tokenPayload) {
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setIssuedAt(Date.from(tokenPayload.issuedAt()))
                .setExpiration(Date.from(tokenPayload.expiration()))
                .signWith(KeyHolder.getKey(env.getSecurity().getSecret()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(KeyHolder.getKey(env.getSecurity().getSecret()))
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidJWTException("Failed to parse the token,Invalid JWT");
        }
    }
}
