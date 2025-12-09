package com.example.evaluationservice.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stars; // valeur 1 → 5

    @OneToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}

