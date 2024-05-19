package com.example.mockapi.repository;


import com.example.mockapi.model.reddit.entity.RedditPostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditPostRepository extends PagingAndSortingRepository<RedditPostEntity, Long>  {
    RedditPostEntity save(RedditPostEntity post);
}
