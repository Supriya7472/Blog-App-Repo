package com.example.Blog_App.exception.handler;

import com.example.Blog_App.exception.ResourceNotFoundException;
import com.example.Blog_App.util.ResponseBuilder;
import com.example.Blog_App.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogsExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handleUnAuthorisedAccess(ResourceNotFoundException ex){
        return ResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage());

    }
}