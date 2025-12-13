package com.onlinetutoring.usersservice;

import com.onlinetutoring.usersservice.service.impl.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ServiceStudentImpl studentService, ServiceTutorImpl tutorService,
            ServiceSubjectImpl serviceSubject, ServiceQualificationImpl serviceQualification) {
        return args -> {
            // Data initialization can be done via Flyway migrations or on-demand
        };
    }

}
