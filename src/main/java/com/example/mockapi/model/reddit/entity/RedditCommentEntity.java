package com.example.mockapi.model.reddit.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reddit_comment")
public class RedditCommentEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long commentId;
    @ManyToOne
    private RedditPostEntity post;
    @ManyToOne
    private RedditCommentEntity parent;
    private String content;
}
