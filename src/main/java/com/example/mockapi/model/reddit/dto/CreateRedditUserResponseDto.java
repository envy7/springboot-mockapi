package com.example.mockapi.model.reddit.dto;

import lombok.Data;

@Data
public class CreateRedditUserResponseDto {
    private Long userId;
    private String username;
}
