package com.example.Blog_App.model;

import com.example.Blog_App.BlogStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "blogs")
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogId;

    @Column(name = "title")
    private String title;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private BlogStatus status = BlogStatus.POSTED;

    @ManyToOne
    private User author;
}
