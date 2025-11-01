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
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private double duration;

    // Session ↔ Material (Many-to-Many)
    @ManyToMany
    @JoinTable(
            name = "session_materials",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private List<Material> materials = new ArrayList<>();

    // Session → Booking (One-to-Many)
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    // Session ↔ Schedule (One-to-One, owning side)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", nullable = false, unique = true)
    private Schedule schedule;
}

