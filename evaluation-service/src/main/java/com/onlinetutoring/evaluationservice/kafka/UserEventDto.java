package com.onlinetutoring.evaluationservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Event DTO for consuming user events from Kafka
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDto implements Serializable {

    private String eventType; // CREATED, UPDATED, DELETED
    private Long userId;
    private String userType; // STUDENT, TUTOR
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime timestamp;
}
