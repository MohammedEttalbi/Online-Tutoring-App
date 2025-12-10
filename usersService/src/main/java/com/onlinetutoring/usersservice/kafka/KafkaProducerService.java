package com.onlinetutoring.usersservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * Kafka Producer Service for publishing user events
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC = "user-events";

    private final KafkaTemplate<String, UserEventDto> kafkaTemplate;

    /**
     * Publish a user event to Kafka
     */
    public void publishUserEvent(Long userId, String userType, String eventType,
            String firstName, String lastName, String email) {
        UserEventDto event = UserEventDto.builder()
                .eventType(eventType)
                .userId(userId)
                .userType(userType)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .timestamp(LocalDateTime.now())
                .build();

        String key = userType + "-" + userId;

        CompletableFuture<SendResult<String, UserEventDto>> future = kafkaTemplate.send(TOPIC, key, event);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Published {} event for {} with id={} to topic={}, partition={}, offset={}",
                        eventType, userType, userId, TOPIC,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Failed to publish {} event for {} with id={}: {}",
                        eventType, userType, userId, ex.getMessage());
            }
        });
    }

    /**
     * Publish student created event
     */
    public void publishStudentCreated(Long studentId, String firstName, String lastName, String email) {
        publishUserEvent(studentId, "STUDENT", "CREATED", firstName, lastName, email);
    }

    /**
     * Publish student updated event
     */
    public void publishStudentUpdated(Long studentId, String firstName, String lastName, String email) {
        publishUserEvent(studentId, "STUDENT", "UPDATED", firstName, lastName, email);
    }

    /**
     * Publish student deleted event
     */
    public void publishStudentDeleted(Long studentId) {
        publishUserEvent(studentId, "STUDENT", "DELETED", null, null, null);
    }

    /**
     * Publish tutor created event
     */
    public void publishTutorCreated(Long tutorId, String firstName, String lastName, String email) {
        publishUserEvent(tutorId, "TUTOR", "CREATED", firstName, lastName, email);
    }

    /**
     * Publish tutor updated event
     */
    public void publishTutorUpdated(Long tutorId, String firstName, String lastName, String email) {
        publishUserEvent(tutorId, "TUTOR", "UPDATED", firstName, lastName, email);
    }

    /**
     * Publish tutor deleted event
     */
    public void publishTutorDeleted(Long tutorId) {
        publishUserEvent(tutorId, "TUTOR", "DELETED", null, null, null);
    }
}
