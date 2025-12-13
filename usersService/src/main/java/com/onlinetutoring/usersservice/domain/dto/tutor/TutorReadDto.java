package com.onlinetutoring.usersservice.domain.dto.tutor;

import com.onlinetutoring.usersservice.domain.dto.qualification.QualificationReadDto;
import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
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
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String bio;
    private Set<SubjectReadDto> subjects;
    private Set<QualificationReadDto> qualifications;
}
