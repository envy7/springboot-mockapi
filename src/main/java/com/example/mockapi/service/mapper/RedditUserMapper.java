package com.example.mockapi.service.mapper;

import com.example.mockapi.model.reddit.dto.RedditUserRequestDto;
import com.example.mockapi.model.reddit.dto.RedditUserResponseDto;
import com.example.mockapi.model.reddit.entity.RedditUserEntity;
import org.springframework.stereotype.Component;

@Component
public class RedditUserMapper implements EntityMapper<RedditUserRequestDto, RedditUserResponseDto, RedditUserEntity> {

    @Override
    public RedditUserEntity toEntity(RedditUserRequestDto redditUserRequestDto) {
        RedditUserEntity redditUserEntity = new RedditUserEntity();
        redditUserEntity.setUsername(redditUserRequestDto.getUsername());
        return redditUserEntity;
    }

    @Override
    public RedditUserResponseDto toDto(RedditUserEntity redditUserEntity) {
        RedditUserResponseDto redditUserResponseDto = new RedditUserResponseDto();
        redditUserResponseDto.setUserId(redditUserEntity.getUserId());
        redditUserResponseDto.setUsername(redditUserEntity.getUsername());
        return redditUserResponseDto;
    }
}
