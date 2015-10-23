package com.tweet.tweet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableResourceServer
public class TweetMain {

    public static void main(String[] args) {
        SpringApplication.run(TweetMain.class, args);
    }

}
