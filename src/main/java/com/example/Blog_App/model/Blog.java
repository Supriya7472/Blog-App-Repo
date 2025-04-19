package com.example.Blog_App.model;

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
    private int blogId;

    @Column(name = "title")
    private String title;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private User author;
}
