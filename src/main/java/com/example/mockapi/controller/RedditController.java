package com.example.mockapi.controller;

import com.example.mockapi.model.reddit.dto.CreateRedditUserRequestDto;
import com.example.mockapi.model.reddit.dto.CreateRedditUserResponseDto;
import com.example.mockapi.model.reddit.entity.RedditPostEntity;
import com.example.mockapi.model.reddit.entity.RedditUserEntity;
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
    public ResponseEntity<Object> createUser(@RequestBody CreateRedditUserRequestDto user) {
        Optional<CreateRedditUserResponseDto> userData = redditService.createUser(user);
        return userData.map(redditUser -> ResponseEntity.created(URI.create("/users/" + redditUser.getUserId()))
                .body((Object) redditUser))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists"));
    }

    // Create Post API
//    @PostMapping("/posts/{userId}")
//    public ResponseEntity<Object> createPost(@RequestBody RedditPostEntity post, @PathVariable Long userId) {
//        Optional<RedditPostEntity> userPost = redditService.createPost(post, userId);
//        return userPost.map(redditPost -> ResponseEntity.created(URI.create("/posts/" + redditPost.getPostId()))
//                        .body((Object) redditPost))
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist"));
//    }
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
//    // Upvote Post API
//    @PutMapping("/posts/{postId}/upvote")
//    public ResponseEntity<Post> upvotePost(@PathVariable Long postId) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
//        post.setUpvotes(post.getUpvotes() + 1);
//        postRepository.save(post);
//        return ResponseEntity.ok(post);
//    }
//
//    // Downvote Post API
//    @PutMapping("/posts/{postId}/downvote")
//    public ResponseEntity<Post> downvotePost(@PathVariable Long postId) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
//        post.setDownvotes(post.getDownvotes() + 1);
//        postRepository.save(post);
//        return ResponseEntity.ok(post);
//    }
}
