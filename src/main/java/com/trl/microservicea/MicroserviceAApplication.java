package com.trl.microservicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceAApplication.class, args);
    }

}
