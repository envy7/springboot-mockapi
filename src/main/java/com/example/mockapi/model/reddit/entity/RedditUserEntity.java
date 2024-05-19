package com.example.mockapi.model.reddit.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reddit_user")
public class RedditUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
}
