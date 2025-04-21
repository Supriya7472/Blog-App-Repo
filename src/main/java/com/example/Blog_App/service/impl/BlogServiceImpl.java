package com.example.Blog_App.service.impl;

import com.example.Blog_App.exception.ResourceNotFoundException;
import com.example.Blog_App.model.Blog;
import com.example.Blog_App.repository.BlogRepository;
import com.example.Blog_App.security.util.UserIdentity;
import com.example.Blog_App.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserIdentity userIdentity;
    @Override
    public Blog createBlog(Blog blog) {

       blog.setCreatedBy(userIdentity.getCurrentUser().getUserName());
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog updatedBlog) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

        if (!existingBlog.getCreatedBy().equals(userIdentity.getCurrentUserEmail())) {
            throw new SecurityException("You are not allowed to update this blog");
        }

        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setContent(updatedBlog.getContent());
        return blogRepository.save(existingBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));

        if (!blog.getCreatedBy().equals(userIdentity.getCurrentUserEmail())) {
            throw new SecurityException("You are not allowed to delete this blog");
        }

        blogRepository.deleteById(id);
    }
//    @Override
//    public List<Blog> getBlogsByAuthorName(String authorName) {
//        return blogRepository.findByAuthor_UserName(authorName);
//    }

    @Override
    public Page<Blog> findAllBlogs(Pageable pageable) {

        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllBlogsByUserEmail(Pageable pageable) {
        String email=userIdentity.getCurrentUserEmail();
        System.out.println(email);
        return blogRepository.findAllByAuthor_Email(email,pageable);
    }

    @Override
    public Blog findBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Failed to find Blog by Id " + id));
    }
}
