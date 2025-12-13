package com.onlinetutoring.usersservice;

import com.onlinetutoring.usersservice.config.TestKafkaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestKafkaConfig.class)
class UsersServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
