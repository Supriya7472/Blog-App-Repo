package com.example.Blog_App.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IllegalActivityException extends RuntimeException {
    private final String message;
}
