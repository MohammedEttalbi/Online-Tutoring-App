package com.onlinetutoring.usersservice.config;

import com.onlinetutoring.usersservice.kafka.UserEventDto;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Test configuration that provides mock Kafka beans.
 * This prevents the need for a real Kafka broker during tests.
 */
@TestConfiguration
public class TestKafkaConfig {

    @Bean
    @Primary
    @SuppressWarnings("unchecked")
    public KafkaTemplate<String, UserEventDto> kafkaTemplate() {
        return Mockito.mock(KafkaTemplate.class);
    }
}
