package com.example.Blog_App.exception.handler;

import com.example.Blog_App.exception.*;
import com.example.Blog_App.util.ResponseBuilder;
import com.example.Blog_App.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handleUserNotFoundByIdException(UserNotFoundException ex){
        return ResponseBuilder.notFound(ex.getMessage());
    }

    @ExceptionHandler(IllegalActivityException.class)
    public ResponseEntity<SimpleErrorResponse> handleIllegalActivityException(IllegalActivityException ex){
        return ResponseBuilder.forbidden(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<SimpleErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return ResponseBuilder.error(HttpStatus.CONFLICT,ex.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<SimpleErrorResponse> handleUnauthenticatedException(UnauthenticatedException ex){
        return ResponseBuilder.error(HttpStatus.UNAUTHORIZED,ex.getMessage());
    }

    @ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<SimpleErrorResponse> handleInvalidJWTException(InvalidJWTException ex){
        return ResponseBuilder.error(HttpStatus.UNAUTHORIZED,ex.getMessage());
    }
}
