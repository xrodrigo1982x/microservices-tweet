package com.tweet.tweet.repository;

import com.tweet.tweet.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TweetRepository extends PagingAndSortingRepository<Tweet, String> {
    Page<Tweet> findByUserIn(List<String> ids, Pageable pageable);

    Page<Tweet> findByContentContainsIgnoreCase(String q, Pageable pageable);
}
