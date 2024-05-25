package com.example.mockapi.model.reddit.dto;

import lombok.Data;

@Data
public class RedditPostResponseDto {
    private Long postId;
    private String content;
    private RedditUserResponseDto user;
    private int upvotes;
    private int downvotes;
}
