package com.tweet.tweet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowInfo {

    @JsonProperty("followed")
    private String id;

}
