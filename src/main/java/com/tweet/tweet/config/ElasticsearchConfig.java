package com.tweet.tweet.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfig {

    @Value("${elasticsearch.addresses:localhost:9300}")
    private String addresses;

    @Bean
    public Client client(){
        TransportClient client = new TransportClient();
        for(String address : addresses.split(",")){
            String[] add = address.split(":");
            client.addTransportAddress(new InetSocketTransportAddress(add[0], Integer.parseInt(add[1])));
        }
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }

}
