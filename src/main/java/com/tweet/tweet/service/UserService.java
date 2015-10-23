package com.tweet.tweet.service;

import com.tweet.tweet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "tweet.user", key = "#username")
    public String findName(String username){
        return userRepository.findUser(username).getName();
    }

}
