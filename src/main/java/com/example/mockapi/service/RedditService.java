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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RedditService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RedditUserRepository redditUserRepository;

    @Autowired
    private RedditPostRepository redditPostRepository;

    @Autowired
    private RedditCommentRepository redditCommentRepository;

    public Optional<RedditUserResponseDto> createUser(RedditUserRequestDto redditUserRequestDto) {
        if (redditUserRepository.findByUsername(redditUserRequestDto.getUsername()) != null) {
            return Optional.empty();  // Handle username already exists
        }
        RedditUserEntity redditUserEntity = modelMapper.map(redditUserRequestDto, RedditUserEntity.class);
        return Optional.of(modelMapper.map(redditUserRepository.save(redditUserEntity), RedditUserResponseDto.class));
    }

    public Optional<RedditPostResponseDto> createPost(RedditPostRequestDto redditPostRequestDto, Long userId) {
        RedditUserEntity user = redditUserRepository.findById(userId);
        if (user == null) {
            return Optional.empty();
        }
        RedditPostEntity redditPostEntity = modelMapper.map(redditPostRequestDto, RedditPostEntity.class);
        redditPostEntity.setUser(user);
        return Optional.of(modelMapper.map(redditPostRepository.save(redditPostEntity), RedditPostResponseDto.class));
    }
}
