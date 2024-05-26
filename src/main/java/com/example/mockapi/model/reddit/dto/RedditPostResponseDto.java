package com.example.mockapi.model.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedditPostResponseDto {
    private Long postId;
    private String content;
    private RedditUserResponseDto user;
    private int upvotes;
    private int downvotes;
}
