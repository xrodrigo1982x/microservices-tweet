package com.tweet.tweet.repository;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("follow")
public interface FollowRepository {

    @RequestMapping(value = "/{id}/timelineusers", method = GET)
    public List<String> findFollowedBy(@PathVariable("id") String id);
}
