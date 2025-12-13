package com.onlinetutoring.usersservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("student")
public class Student extends User {
    @Column(length = 60)
    private String level;
}
