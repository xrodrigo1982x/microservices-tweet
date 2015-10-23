package com.tweet.tweet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "tweet")
public class Tweet {

    @Id
    private String id;
    private String content;
    private String user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Field(index = FieldIndex.analyzed)
    private Date date;
    private Location location;
    private Set<String> hashtags;
    @Transient
    private String name;

}
