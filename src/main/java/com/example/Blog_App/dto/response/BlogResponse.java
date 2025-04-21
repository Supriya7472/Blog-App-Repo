package com.example.Blog_App.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BlogResponse {
    private Long blogId;
    private String title;
    private String content;
    private String createdBy;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
