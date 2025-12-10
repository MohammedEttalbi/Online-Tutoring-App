package com.onlinetutoring.usersservice.domain.dto.user;

import com.onlinetutoring.usersservice.domain.dto.subject.SubjectReadDto;
import com.onlinetutoring.usersservice.domain.enums.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserReadDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    Role role;
    Set<SubjectReadDto> subjects;
}
