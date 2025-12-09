package com.onlinetutoring.usersservice.domain.dto.tutor;

import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationReadDto;
import com.onlinetutoring.usersservice.domain.enums.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Tutor}
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TutorReadDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    Role role;
    String bio;
    Set<QualificationReadDto> qualifications;

}