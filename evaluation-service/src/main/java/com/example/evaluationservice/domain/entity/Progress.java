package com.example.evaluationservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long tutorId;

    private Integer score;  // exemple 0→100
    private String level;   // Débutant / Intermédiaire / Avancé

    private LocalDateTime updatedAt;
}

