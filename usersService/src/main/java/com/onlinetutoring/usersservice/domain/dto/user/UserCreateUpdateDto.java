package com.onlinetutoring.usersservice.domain.dto.user;

import com.onlinetutoring.usersservice.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.User}
 */

@Getter
@Setter
@AllArgsConstructor
public class UserCreateUpdateDto implements Serializable {
    @NotNull
    @Size(max = 80)
    @NotEmpty
    @NotBlank
    String firstName;
    @NotNull
    @Size(max = 80)
    @NotEmpty
    @NotBlank
    String lastName;
    @NotNull
    @Size(max = 80)
    @Email
    @NotEmpty
    @NotBlank
    String email;
    @NotNull
    Role role;
}