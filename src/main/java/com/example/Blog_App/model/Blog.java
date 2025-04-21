package com.example.Blog_App.model;

import com.example.Blog_App.BlogStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private BlogStatus status = BlogStatus.POSTED;

    @CreatedDate
    @Column(name = "createdAt")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "lastModifiedAt")
    private Instant lastModifiedAt;

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @ManyToOne
    @JsonIgnore
    private User author;
}
