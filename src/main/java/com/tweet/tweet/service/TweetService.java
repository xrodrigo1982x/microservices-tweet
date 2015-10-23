package com.tweet.tweet.service;

import com.tweet.tweet.model.Tweet;
import com.tweet.tweet.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    public Tweet save(Tweet tweet){
        tweet.setDate(new Date());
        tweet.setHashtags(extractHashtags(tweet.getContent()));
        return tweetRepository.save(tweet);
    }

    public Tweet findById(String id) {
        return setName(tweetRepository.findOne(id));
    }

    private Tweet setName(Tweet one) {
        one.setName(userService.findName(one.getUser()));
        return one;
    }

    private Set<String> extractHashtags(String content){
        Pattern pattern = Pattern.compile("#[a-zA-Z]+");
        Matcher matcher = pattern.matcher(content);
        Set<String> tags = new HashSet<>();
        while(matcher.find()){
            tags.add(matcher.group());
        }
        return tags;
    }

    public Page<Tweet> getTimeline(String userId, Pageable page) {
        List<String> users = followService.followedBy(userId);
        users.add(userId);
        Page<Tweet> tweets = tweetRepository.findByUserIn(users, page);
        tweets.getContent().parallelStream().forEach(t -> setName(t));
        return tweets;
    }

    public Page<Tweet> search(String q, PageRequest pageRequest) {
        return tweetRepository.findByContentContainsIgnoreCase(q, pageRequest);
    }
}
