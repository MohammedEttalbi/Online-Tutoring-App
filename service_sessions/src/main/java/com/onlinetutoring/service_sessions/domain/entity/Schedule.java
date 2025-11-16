package com.onlinetutoring.service_sessions.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Specific date/time for the session
    @Column(nullable = false)
    private LocalDateTime dateTime;

    // Inverse side of Session ↔ Schedule (One-to-One)
    @OneToOne(mappedBy = "schedule")
    private Session session;
}

