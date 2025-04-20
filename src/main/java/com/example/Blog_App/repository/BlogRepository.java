package com.example.Blog_App.repository;

import com.example.Blog_App.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findByAuthor_UserName(String name);
}
