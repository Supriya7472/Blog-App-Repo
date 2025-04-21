package com.example.Blog_App.mapper;

import com.example.Blog_App.dto.request.BlogRequest;
import com.example.Blog_App.dto.request.UserRegistrationRequest;
import com.example.Blog_App.dto.response.BlogResponse;
import com.example.Blog_App.dto.response.UserResponse;
import com.example.Blog_App.model.Blog;
import com.example.Blog_App.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface BlogMapper {
    Blog mapToBlog(BlogRequest blogRequest);

    BlogResponse mapToBlogResponse(Blog blog);

    void mapToUpdatedBlog(BlogRequest request, @MappingTarget Blog blog);
}
