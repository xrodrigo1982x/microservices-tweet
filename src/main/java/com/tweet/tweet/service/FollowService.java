package com.tweet.tweet.service;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.tweet.tweet.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Cacheable(value = "follow.timeline", key = "#id")
    public List<String> followedBy(String id){
        return followRepository.findFollowedBy(id);
    }

}
