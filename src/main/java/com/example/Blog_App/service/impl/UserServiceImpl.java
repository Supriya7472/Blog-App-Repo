package com.example.Blog_App.service.impl;

import com.example.Blog_App.dto.request.UserRegistrationRequest;
import com.example.Blog_App.dto.request.UserUpdateRequest;
import com.example.Blog_App.dto.response.UserResponse;
import com.example.Blog_App.exception.UserAlreadyExistsException;
import com.example.Blog_App.exception.UserNotFoundException;
import com.example.Blog_App.mapper.UserMapper;
import com.example.Blog_App.model.Blog;
import com.example.Blog_App.model.User;
import com.example.Blog_App.repository.BlogRepository;
import com.example.Blog_App.repository.UserRepository;
import com.example.Blog_App.security.util.UserIdentity;
import com.example.Blog_App.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final BlogRepository blogRepository;
    private final UserIdentity userIdentity;

    @Override
    public UserResponse registerUser(UserRegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + registrationRequest.getEmail() + " already exists.");
        }

        User user = userMapper.mapToUser(registrationRequest);
        user.setUserName(registrationRequest.getUserName());
        user.setPhNo(registrationRequest.getPhno());
        this.encryptPassword(user);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    private void encryptPassword(User user){
        String encodedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Override
    public UserResponse findUserByEmail() {

        User user =userIdentity.getCurrentUser();
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserByEmail(UserUpdateRequest userRequest) {
        User exUser=userIdentity.getCurrentUser();
        userIdentity.validateOwnerShip(exUser.getEmail());

        userMapper.mapToUpdateUser(userRequest, exUser);
        User updatedUser = userRepository.save(exUser);
        return userMapper.mapToUserResponse(updatedUser);
    }

    @Override
    public List<UserResponse> getAuthorsByBlogTitle(String blogTitle) {
        List<Blog> blogs = blogRepository.findAllByTitle(blogTitle);

        // Extract distinct authors
        List<User> authors = blogs.stream()
                .map(Blog::getAuthor)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // If no authors found, throw exception
        if (authors.isEmpty()) {
            throw new UserNotFoundException("Author not found for blog title: " + blogTitle);
        }

        // Convert to UserResponse
        return authors.stream()
                .map(userMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }



}
