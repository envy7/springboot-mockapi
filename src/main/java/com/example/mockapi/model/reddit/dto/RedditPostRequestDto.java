package com.example.mockapi.model.reddit.dto;

import lombok.Data;

@Data
public class RedditPostRequestDto {
    private String content;
    private int upvotes;
    private int downvotes;
}
