package com.example.mockapi.model.reddit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "reddit_post")
public class RedditPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private RedditUserEntity user;
    private String content;
    private int upvotes;
    private int downvotes;
}
