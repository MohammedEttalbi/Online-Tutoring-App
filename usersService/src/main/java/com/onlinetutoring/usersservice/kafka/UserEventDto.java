package com.onlinetutoring.usersservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Event DTO for publishing user events to Kafka
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
