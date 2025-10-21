package com.onlinetutoring.usersservice.domain.dto.student;

import com.onlinetutoring.usersservice.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Student}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
public class StudentReadDto implements Serializable {
    String firstName;
    String lastName;
    String email;
    Role role;
    String level;
}