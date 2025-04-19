package com.example.Blog_App.mapper;

import com.example.Blog_App.dto.request.UserRegistrationRequest;
import com.example.Blog_App.dto.response.UserResponse;
import com.example.Blog_App.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface UserMapper {

    User mapToUser(UserRegistrationRequest user);

    UserResponse mapToUserResponse(User user);

    void mapToUpdateUser(UserRegistrationRequest request, @MappingTarget User user);


}
