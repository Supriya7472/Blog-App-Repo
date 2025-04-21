package com.example.Blog_App.security.filters;

import com.example.Blog_App.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;

public class FilterHelper {

    public static String getToken(TokenType tokenType, Cookie[] cookies){
        String token=null;
        for(Cookie cookie:cookies){
            if (tokenType.type().equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}
