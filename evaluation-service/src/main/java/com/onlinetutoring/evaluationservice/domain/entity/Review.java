package com.onlinetutoring.evaluationservice.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;  // venant du microservice User/Student
    private Long tutorId;    // venant du microservice User/Tutor

    @Column(length = 1000)
    private String comment;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Rating rating;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList;
}
