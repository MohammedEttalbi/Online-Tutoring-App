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
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Long studentId;

    // Booking → Session (Many-to-One)
    @ManyToOne(optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
