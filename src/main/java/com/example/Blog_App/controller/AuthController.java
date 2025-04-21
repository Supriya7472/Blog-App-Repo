package com.example.Blog_App.controller;

import com.example.Blog_App.dto.request.Auth;
import com.example.Blog_App.dto.request.LoginRequest;
import com.example.Blog_App.exception.InvalidJWTException;
import com.example.Blog_App.security.jwt.ClaimName;
import com.example.Blog_App.security.jwt.JWTService;
import com.example.Blog_App.security.jwt.TokenType;
import com.example.Blog_App.service.AuthService;
import com.example.Blog_App.service.TokenGenerationService;
import com.example.Blog_App.service.helper.CookieManager;
import com.example.Blog_App.util.ResponseBuilder;
import com.example.Blog_App.util.ResponseStructure;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class AuthController {

    private final AuthService authService;
    private final TokenGenerationService tokenGenerationService;
    private final JWTService jwtService;
    private final CookieManager cookieManager;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<Auth>> login(@RequestBody LoginRequest loginRequest){
        Auth auth=authService.login(loginRequest);
        HttpHeaders httpHeaders =tokenGenerationService.grantAccessAndRefreshTokens(auth);
        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "Login Successful", auth);
    }

    @PostMapping("/refresh-login")
    public ResponseEntity<ResponseStructure<Auth>> refreshLogin(@CookieValue("rt") String refreshToken){

        Claims claims = jwtService.parseToken(refreshToken);
        Instant accessExpiration = Instant.ofEpochSecond(authService.calculateNewAccessExpiration());
        Auth auth = new Auth(
                claims.get(ClaimName.USER_ID, Long.class),
                "",
                claims.get(ClaimName.USER_EMAIL, String.class),
                accessExpiration.toEpochMilli(),
                claims.getExpiration().getTime()
        );

        auth= authService.refreshLogin(auth);
        HttpHeaders httpHeaders =tokenGenerationService.reissueAccessToken(auth);

        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "Login Successful", auth);

    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseStructure<String>> logout() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieManager.generateCookie(TokenType.ACCESS, "", 0L));
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieManager.generateCookie(TokenType.REFRESH, "", 0L));

        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "Logout Successful", "You have been logged out.");
    }


    @GetMapping("/auth")
    public ResponseEntity<ResponseStructure<Auth>> getAuthRecord(@CookieValue(required = true, name = "at") String accessToken) {
        try {
            Auth auth = authService.getAuthRecordByAccessToken(accessToken);
            return ResponseBuilder.success(HttpStatus.OK, "Auth record fetched successfully", auth);
        } catch (Exception e) {
            throw new InvalidJWTException("Invalid or expired token");
        }
    }


}
