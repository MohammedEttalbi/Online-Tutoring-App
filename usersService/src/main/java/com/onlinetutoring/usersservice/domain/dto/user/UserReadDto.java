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
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Set<SubjectReadDto> subjects;
}
