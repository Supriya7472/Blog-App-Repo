package com.example.Blog_App.controller;

import com.example.Blog_App.dto.response.BlogResponse;
import com.example.Blog_App.exception.UnauthenticatedException;
import com.example.Blog_App.mapper.BlogMapper;
import com.example.Blog_App.model.Blog;
import com.example.Blog_App.security.util.UserIdentity;
import com.example.Blog_App.service.BlogService;
import com.example.Blog_App.util.ResponseBuilder;
import com.example.Blog_App.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final BlogMapper blogMapper;
    private final UserIdentity userIdentity;


    @PostMapping("/blogs")
    public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@RequestBody Blog blog) {

        Blog createdBlog = blogService.createBlog(blog);
        BlogResponse blogResponse = blogMapper.mapToBlogResponse(createdBlog);
        return ResponseBuilder.created("Blog Created successfully", blogResponse);
    }


    @PutMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(
            @PathVariable Long id,
            @RequestBody Blog updatedBlog) {


        Blog blog = blogService.updateBlog(id, updatedBlog);
        BlogResponse blogResponse = blogMapper.mapToBlogResponse(blog);
        return ResponseBuilder.ok("Blog Updated Successfully", blogResponse);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/blogs")
    public ResponseEntity<Page<Blog>> findAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> blogs = blogService.findAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(@PathVariable Long id) {
        Blog blog = blogService.findBlogById(id);
        return ResponseBuilder.ok("Blog Found", blogMapper.mapToBlogResponse(blog));
    }

    @GetMapping("/blogs/user")
    public ResponseEntity<Page<Blog>> findAllBlogsByUserEmail(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> blogs = blogService.findAllBlogsByUserEmail(pageable);
        return ResponseEntity.ok(blogs);
    }


}
