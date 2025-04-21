package com.example.Blog_App.controller;

import com.example.Blog_App.dto.request.UserRegistrationRequest;
import com.example.Blog_App.dto.request.UserUpdateRequest;
import com.example.Blog_App.dto.response.UserResponse;
import com.example.Blog_App.model.User;
import com.example.Blog_App.service.UserService;
import com.example.Blog_App.util.ResponseBuilder;
import com.example.Blog_App.util.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(
            @RequestBody @Valid UserRegistrationRequest registrationRequest) {
        UserResponse userResponse = userService.registerUser(registrationRequest);
        return ResponseBuilder.created("User Created", userResponse);
    }

    @GetMapping("/account")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail() {
        UserResponse userResponse = userService.findUserByEmail();
        return ResponseBuilder.ok("User Found", userResponse);
    }


    @PutMapping("/users")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserByEmail(
            @RequestBody @Valid UserUpdateRequest userRequest) {
        UserResponse userResponse = userService.updateUserByEmail(userRequest);
        return ResponseBuilder.ok("User Updated", userResponse);
    }
//
//    @GetMapping("/blog/{blogTitle}")
//    public ResponseEntity<ResponseStructure<List<UserResponse>>> getAuthorByBlogTitle(@PathVariable String blogTitle) {
//        List<UserResponse> userResponses  = userService.getAuthorsByBlogTitle(blogTitle);
//        return ResponseBuilder.found("User Found with Blog title "+blogTitle,userResponses);
//    }

}
