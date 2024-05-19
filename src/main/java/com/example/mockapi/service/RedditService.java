package com.example.mockapi.service;

import com.example.mockapi.model.reddit.dto.CreateRedditUserRequestDto;
import com.example.mockapi.model.reddit.dto.CreateRedditUserResponseDto;
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

    public Optional<CreateRedditUserResponseDto> createUser(CreateRedditUserRequestDto createRedditUserRequestDto) {
        if (redditUserRepository.findByUsername(createRedditUserRequestDto.getUsername()) != null) {
            return Optional.empty();  // Handle username already exists
        }
        RedditUserEntity redditUserEntity = modelMapper.map(createRedditUserRequestDto, RedditUserEntity.class);
        return Optional.of(modelMapper.map(redditUserRepository.save(redditUserEntity), CreateRedditUserResponseDto.class));
    }

//    public Optional<RedditPostEntity> createPost(RedditPostEntity post, Long userId) {
//        RedditUserEntity user = redditUserRepository.getReferenceById(userId);
//        if (user == null) {
//            return Optional.empty();
//        }
//        post.setUser(user);
//        return Optional.of(redditPostRepository.save(post));
//    }

}
