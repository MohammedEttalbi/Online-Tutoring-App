package com.onlinetutoring.evaluationservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score; // 1 à 5
    private String criteria; // ex: Performance, Communication, etc.

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;
}

