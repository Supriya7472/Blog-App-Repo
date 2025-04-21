package com.example.Blog_App.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnauthenticatedException extends RuntimeException {
    private final String message;
}
