package com.example.mockapi.controller;

import com.example.mockapi.model.reddit.dto.RedditPostRequestDto;
import com.example.mockapi.model.reddit.dto.RedditPostResponseDto;
import com.example.mockapi.model.reddit.dto.RedditUserRequestDto;
import com.example.mockapi.model.reddit.dto.RedditUserResponseDto;
import com.example.mockapi.service.RedditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/reddit")
public class RedditController {
    @Autowired
    private RedditService redditService;

    // Create User API
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody RedditUserRequestDto user) {
        Optional<RedditUserResponseDto> userData = redditService.createUser(user);
        return userData.map(redditUser -> ResponseEntity.created(URI.create("/users/" + redditUser.getUserId()))
                .body((Object) redditUser))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists"));
    }

    // Create Post API
    @PostMapping("/posts/{userId}")
    public ResponseEntity<Object> createPost(@RequestBody RedditPostRequestDto post, @PathVariable Long userId) {
        Optional<RedditPostResponseDto> userPost = redditService.createPost(post, userId);
        return userPost.map(redditPost -> ResponseEntity.created(URI.create("/posts/" + redditPost.getPostId()))
                        .body((Object) redditPost))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist"));
    }
//
//    // Create Comment API
//    @PostMapping("/posts/{postId}/comments")
//    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
//        comment.setPost(post);
//        Comment savedComment = commentRepository.save(comment);
//        return ResponseEntity.ok(savedComment);
//    }
//
}
