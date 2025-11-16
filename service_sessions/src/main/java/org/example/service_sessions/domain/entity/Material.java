package org.example.service_sessions.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private String type;

    // Inverse side of Session ↔ Material (Many-to-Many)
    @ManyToMany(mappedBy = "materials")
    private List<Session> sessions = new ArrayList<>();
}

