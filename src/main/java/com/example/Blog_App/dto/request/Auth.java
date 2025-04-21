package com.example.Blog_App.dto.request;

public record Auth(Long userId,
                   String username,
                   String email,
                   Long accessExpiration,
                   Long refreshExpiration) {
}
