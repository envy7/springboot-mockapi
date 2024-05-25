package com.example.mockapi.service;

import com.example.mockapi.model.reddit.dto.RedditPostRequestDto;
import com.example.mockapi.model.reddit.dto.RedditPostResponseDto;
import com.example.mockapi.model.reddit.dto.RedditUserRequestDto;
import com.example.mockapi.model.reddit.dto.RedditUserResponseDto;
import com.example.mockapi.model.reddit.entity.RedditPostEntity;
import com.example.mockapi.model.reddit.entity.RedditUserEntity;
import com.example.mockapi.repository.RedditCommentRepository;
import com.example.mockapi.repository.RedditPostRepository;
import com.example.mockapi.repository.RedditUserRepository;
import com.example.mockapi.service.mapper.RedditPostMapper;
import com.example.mockapi.service.mapper.RedditUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RedditService {
    @Autowired
    private RedditUserRepository redditUserRepository;
    @Autowired
    private RedditPostRepository redditPostRepository;
    @Autowired
    private RedditCommentRepository redditCommentRepository;
    @Autowired
    private RedditUserMapper redditUserMapper;
    @Autowired
    private RedditPostMapper redditPostMapper;

    public Optional<RedditUserResponseDto> createUser(RedditUserRequestDto redditUserRequestDto) {
        if (redditUserRepository.findByUsername(redditUserRequestDto.getUsername()) != null) {
            return Optional.empty();  // Handle username already exists
        }
        RedditUserEntity redditUserEntity = redditUserMapper.toEntity(redditUserRequestDto);
        return Optional.of(redditUserMapper.toDto(redditUserRepository.save(redditUserEntity)));
    }

    public Optional<RedditPostResponseDto> createPost(RedditPostRequestDto redditPostRequestDto, Long userId) {
        RedditUserEntity user = redditUserRepository.findById(userId);
        if (user == null) {
            return Optional.empty();
        }
        RedditPostEntity redditPostEntity = redditPostMapper.toEntity(redditPostRequestDto);
        redditPostEntity.setUser(user);
        return Optional.of(redditPostMapper.toDto(redditPostRepository.save(redditPostEntity)));
    }
}
