package com.example.mockapi.repository;

import com.example.mockapi.model.reddit.entity.RedditUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditUserRepository extends PagingAndSortingRepository<RedditUserEntity, Long> {
    RedditUserEntity findByUsername(String username);
    RedditUserEntity save(RedditUserEntity user);
    RedditUserEntity findById(Long userId);
    RedditUserEntity getReferenceById(Long userId);
}
