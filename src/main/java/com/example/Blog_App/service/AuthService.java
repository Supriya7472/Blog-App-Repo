package com.example.Blog_App.service;

import com.example.Blog_App.dto.request.Auth;
import com.example.Blog_App.dto.request.LoginRequest;
import org.springframework.stereotype.Service;

public interface AuthService {
    Auth login(LoginRequest loginRequest);
    Auth refreshLogin(Auth auth);
    Long calculateNewAccessExpiration();
    Auth getAuthRecordByAccessToken(String accessToken);
}
