package com.trl.microservicea.conf;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Configuration {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }
}
