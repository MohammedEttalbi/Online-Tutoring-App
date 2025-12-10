package com.onlinetutoring.usersservice.domain.dto.student;

import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
import com.onlinetutoring.usersservice.domain.enums.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Student}
 */
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor

public class StudentReadDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    Role role;
    String level;
    Set<SubjectReadDto> subjects;
}
