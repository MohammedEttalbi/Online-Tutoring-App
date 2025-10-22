package com.onlinetutoring.usersservice.domain.dto.user;

import com.onlinetutoring.usersservice.domain.enums.Role;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.User}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserReadDto implements Serializable {
    String firstName;
    String lastName;
    String email;
    Role role;
}