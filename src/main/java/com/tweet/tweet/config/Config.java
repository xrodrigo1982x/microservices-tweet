package com.tweet.tweet.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.tweet.tweet.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = FollowRepository.class)
@EnableCaching
@EnableHystrix
public class Config {

    @Value("${hazelcast.endpoint:localhost:5701}")
    private String hazelcastEndpoint;

    @Bean
    public CacheManager cacheManager(){
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress(hazelcastEndpoint);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        return new HazelcastCacheManager(client);
    }

}
