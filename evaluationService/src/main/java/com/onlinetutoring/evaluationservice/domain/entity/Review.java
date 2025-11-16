package com.onlinetutoring.evaluationservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName;
    private String subject; // ex: Coach ou Programme
    private String description;
    private LocalDate reviewDate = LocalDate.now();

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Rating rating;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Progress> progresses = new ArrayList<>();
}

