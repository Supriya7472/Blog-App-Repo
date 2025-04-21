package com.example.Blog_App.service;

import com.example.Blog_App.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogService {

    Blog createBlog(Blog blog);

    Blog updateBlog(Long id, Blog updatedBlog);

    void deleteBlog(Long id);

    // public List<Blog> getBlogsByAuthorName(String authorName);
    Page<Blog> findAllBlogs(Pageable pageable);

    Page<Blog> findAllBlogsByUserEmail(Pageable pageable);

    Blog findBlogById(Long id);
}
