package com.onlinetutoring.usersservice.domain.entity;

import jakarta.persistence.*;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Qualification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, length = 180)
    private String title;

    @Column(length = 180)
    private String institution;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
