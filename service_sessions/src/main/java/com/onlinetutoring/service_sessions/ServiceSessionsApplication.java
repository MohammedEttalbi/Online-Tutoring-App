package com.onlinetutoring.service_sessions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSessionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSessionsApplication.class, args);
    }

}
