package com.example.Blog_App.security.filters;

import com.example.Blog_App.security.jwt.ClaimName;
import com.example.Blog_App.security.jwt.JWTService;
import com.example.Blog_App.security.jwt.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Validating request, finding token : {}", TokenType.ACCESS.type());
        Cookie[] cookies = request.getCookies();

        String token = cookies != null ? FilterHelper.getToken(TokenType.ACCESS, cookies) : null;
        if (token != null) {
            log.info("Token found with name:{}", TokenType.ACCESS.type());
            String email = jwtService.parseToken(token).get(ClaimName.USER_EMAIL, String.class);

            if ((email != null && !email.isBlank())) {
                log.info("Claims:{} extracted successfully", ClaimName.USER_EMAIL);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                            UsernamePasswordAuthenticationToken
                            (email, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                    usernamePasswordAuthenticationToken.setDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }
            } else
                log.error("Claims:{} not found", ClaimName.USER_EMAIL);
        } else
            log.warn("Token not found with name :{}", TokenType.ACCESS.type());

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info("Request Authentication successful, username: {}, isAuthenticated: {}", auth.getName(), auth.isAuthenticated());
        } else {
            log.info("Authentication is null. User is unauthenticated.");
        }

        filterChain.doFilter(request, response);
    }

}
