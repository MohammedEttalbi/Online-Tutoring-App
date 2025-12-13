package com.onlinetutoring.usersservice.domain.dto.tutor;

import com.onlinetutoring.usersservice.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Tutor}
 */

@Getter
@Setter
@AllArgsConstructor
public class TutorCreateUpdateDto implements Serializable {
    @NotNull
    @Size(min = 80)
    @NotEmpty
    @NotBlank
    String firstName;
    @NotNull
    @Size(min = 80)
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
    @NotNull
    @Size(max = 80)
    @NotEmpty
    @NotBlank
    String bio;
}