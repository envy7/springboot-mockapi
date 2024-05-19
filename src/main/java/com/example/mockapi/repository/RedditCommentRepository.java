package com.example.mockapi.repository;

import com.example.mockapi.model.reddit.entity.RedditCommentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditCommentRepository extends PagingAndSortingRepository<RedditCommentEntity, Long> {
}
