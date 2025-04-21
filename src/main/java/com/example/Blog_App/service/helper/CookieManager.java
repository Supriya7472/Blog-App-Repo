package com.example.Blog_App.service.helper;

import com.example.Blog_App.config.AppEnv;
import com.example.Blog_App.security.jwt.TokenType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CookieManager {

    private final AppEnv env;

    public String generateCookie(TokenType tokenType, String token, Long maxAge){
        return ResponseCookie.from(tokenType.type(), token)
                .path("/")
                .httpOnly(true)
                .secure(env.getDomain().isSecure())
                .maxAge(maxAge).build().toString();
    }
}
