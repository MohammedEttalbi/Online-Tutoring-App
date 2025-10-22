package com.onlinetutoring.usersservice.domain.dto.tutor;

import com.onlinetutoring.usersservice.domain.enums.Role;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Tutor}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TutorReadDto implements Serializable {
    String firstName;
    String lastName;
    String email;
    Role role;
    String bio;
}