package com.example.Blog_App.mapper;

import com.example.Blog_App.dto.request.UserRegistrationRequest;
import com.example.Blog_App.dto.request.UserUpdateRequest;
import com.example.Blog_App.dto.response.UserResponse;
import com.example.Blog_App.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel ="spring")
public interface UserMapper {

    User mapToUser(UserRegistrationRequest user);

    UserResponse mapToUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapToUpdateUser(UserUpdateRequest request, @MappingTarget User user);



}
