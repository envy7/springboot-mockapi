package com.example.mockapi.model.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedditPostRequestDto {
    private String content;
    private int upvotes;
    private int downvotes;
}
