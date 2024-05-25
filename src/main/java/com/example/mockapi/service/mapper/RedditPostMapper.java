package com.example.mockapi.service.mapper;

import com.example.mockapi.model.reddit.dto.RedditPostRequestDto;
import com.example.mockapi.model.reddit.dto.RedditPostResponseDto;
import com.example.mockapi.model.reddit.entity.RedditPostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedditPostMapper implements EntityMapper<RedditPostRequestDto, RedditPostResponseDto, RedditPostEntity> {

    @Autowired
    RedditUserMapper redditUserMapper;
    @Override
    public RedditPostEntity toEntity(RedditPostRequestDto redditPostRequestDto) {
        RedditPostEntity redditPostEntity = new RedditPostEntity();
        redditPostEntity.setContent(redditPostRequestDto.getContent());
        redditPostEntity.setDownvotes(redditPostRequestDto.getDownvotes());
        redditPostEntity.setUpvotes(redditPostRequestDto.getUpvotes());
        return redditPostEntity;
    }

    @Override
    public RedditPostResponseDto toDto(RedditPostEntity redditPostEntity) {
        RedditPostResponseDto redditPostResponseDto = new RedditPostResponseDto();
        redditPostResponseDto.setUser(redditUserMapper.toDto(redditPostEntity.getUser()));
        redditPostResponseDto.setPostId(redditPostEntity.getPostId());
        redditPostResponseDto.setContent(redditPostEntity.getContent());
        redditPostResponseDto.setUpvotes(redditPostEntity.getUpvotes());
        redditPostResponseDto.setDownvotes(redditPostEntity.getDownvotes());
        return redditPostResponseDto;
    }
}
