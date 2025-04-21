package com.example.Blog_App.service.impl;

import com.example.Blog_App.config.AppEnv;
import com.example.Blog_App.dto.request.Auth;
import com.example.Blog_App.dto.request.LoginRequest;
import com.example.Blog_App.exception.UserNotFoundException;
import com.example.Blog_App.model.User;
import com.example.Blog_App.repository.UserRepository;
import com.example.Blog_App.security.jwt.ClaimName;
import com.example.Blog_App.security.jwt.JWTService;
import com.example.Blog_App.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AppEnv env;
    @Override
    public Auth login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(token);
        Optional<User> user;
        if (authentication.isAuthenticated()) {
            user = userRepository.findByEmail(loginRequest.email());
            return generateAuth(user);

        } else
            throw new UsernameNotFoundException("Failed to Authenticate.");

    }

    private static Auth generateAuth(Optional<User> user) {
        Instant now=Instant.now();
        Long accessExpiration=now.plusSeconds(3600).toEpochMilli();
        Long refreshExpiration=now.plusSeconds(129600).toEpochMilli();
        Auth auth = new Auth(user.get().getUserId(),
                user.get().getUserName(),
                user.get().getEmail(),
                accessExpiration,
                refreshExpiration);
        return auth;
    }


    public Auth refreshLogin(Auth auth) {
        Instant now = Instant.now();
        Long newAccessExpiration = now.plusSeconds(3600).toEpochMilli();

        return new Auth(
                auth.userId(),
                auth.username(),
                auth.email(),
                newAccessExpiration,
                auth.refreshExpiration()
        );

    }

    public Long calculateNewAccessExpiration() {
        long accessValidity = env.getSecurity().getTokenValidity().getAccessValidity();
        return Instant.now().plusSeconds(accessValidity).toEpochMilli();
    }


    /**
     * Fetch the Auth record by validating the access token.
     *
     * @param accessToken - The access token extracted from cookies.
     * @return Auth - The authenticated user information.
     * @throws Exception if the token is invalid or expired.
     */
    public Auth getAuthRecordByAccessToken(String accessToken)  {
        // Parse and validate the access token
        Claims claims = jwtService.parseToken(accessToken);

        // Extract user information from token claims
        Long userId = claims.get(ClaimName.USER_ID, Long.class);
        String userEmail = claims.get(ClaimName.USER_EMAIL, String.class);
        Long accessExpiration = claims.getExpiration().getTime();
        Long refreshExpiration = claims.getExpiration().getTime();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));


        Auth auth = new Auth(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                accessExpiration,
                refreshExpiration
        );

        return auth;
    }



}
