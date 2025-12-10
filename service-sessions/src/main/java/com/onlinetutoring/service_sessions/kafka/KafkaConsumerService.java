package com.onlinetutoring.service_sessions.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka Consumer Service for receiving user events
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private static final String TOPIC = "user-events";
    private static final String GROUP_ID = "service-sessions";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consumeUserEvent(UserEventDto event) {
        log.info("Received {} event: userType={}, userId={}, name={} {}, email={}",
                event.getEventType(),
                event.getUserType(),
                event.getUserId(),
                event.getFirstName(),
                event.getLastName(),
                event.getEmail());

        // Handle different event types
        switch (event.getEventType()) {
            case "CREATED":
                handleUserCreated(event);
                break;
            case "UPDATED":
                handleUserUpdated(event);
                break;
            case "DELETED":
                handleUserDeleted(event);
                break;
            default:
                log.warn("Unknown event type: {}", event.getEventType());
        }
    }

    private void handleUserCreated(UserEventDto event) {
        log.info("Processing {} CREATED event for id={}", event.getUserType(), event.getUserId());
        // Add custom logic here, e.g., validate tutor for sessions
    }

    private void handleUserUpdated(UserEventDto event) {
        log.info("Processing {} UPDATED event for id={}", event.getUserType(), event.getUserId());
        // Add custom logic here, e.g., update cached user info
    }

    private void handleUserDeleted(UserEventDto event) {
        log.info("Processing {} DELETED event for id={}", event.getUserType(), event.getUserId());
        // Add custom logic here, e.g., cleanup sessions/bookings for deleted user
    }
}
