package com.example.Blog_App.service;

import com.example.Blog_App.dto.request.UserRegistrationRequest;
import com.example.Blog_App.dto.request.UserUpdateRequest;
import com.example.Blog_App.dto.response.UserResponse;
import com.example.Blog_App.model.User;

import java.util.List;

public interface UserService {

    public UserResponse registerUser(UserRegistrationRequest registrationRequest);

    public UserResponse findUserByEmail();

    public UserResponse updateUserByEmail(UserUpdateRequest userRequest);

    public List<UserResponse> getAuthorsByBlogTitle(String blogTitle);
}
