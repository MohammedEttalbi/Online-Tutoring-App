package com.onlinetutoring.usersservice.domain.dto.student;

import com.onlinetutoring.usersservice.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Student}
 */
@Getter
@Setter
@AllArgsConstructor
public class StudentCreateUpdateDto implements Serializable {
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
    @NotNull
    @Size
    @NotEmpty
    @NotBlank
    String level;
}