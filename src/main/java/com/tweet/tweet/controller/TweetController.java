package com.tweet.tweet.controller;

import com.tweet.tweet.model.Tweet;
import com.tweet.tweet.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @RequestMapping(value = "", method = POST)
    public ResponseEntity save(@AuthenticationPrincipal Principal token, @RequestBody Tweet tweet){
        tweet.setUser(token.getName());
        return new ResponseEntity(tweetService.save(tweet), OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity findById(@PathVariable String id){
        Tweet tweet = tweetService.findById(id);
        return tweet != null ? ok(tweet) : new ResponseEntity(NOT_FOUND);
    }

    @RequestMapping("timeline/{userId}")
    public ResponseEntity timeline(@PathVariable String userId, @RequestParam(required = false, defaultValue = "0") Integer page){
        PageRequest pageRequest = new PageRequest(page, 10, Sort.Direction.DESC, "date");
        return new ResponseEntity(tweetService.getTimeline(userId, pageRequest), OK);
    }

    @RequestMapping("search")
    public ResponseEntity search(String q, @RequestParam(required = false, defaultValue = "0") Integer page){
        PageRequest pageRequest = new PageRequest(page, 10, Sort.Direction.DESC, "date");
        return new ResponseEntity(tweetService.search(q, pageRequest), OK);
    }

}
