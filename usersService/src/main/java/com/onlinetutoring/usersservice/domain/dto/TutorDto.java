package com.onlinetutoring.usersservice.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Tutor}
 */
@Value
public class TutorDto implements Serializable {
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
    @Size(max = 120)
    @Email
    @NotEmpty
    @NotBlank
    String email;
    @NotNull
    @Size
    @NotEmpty
    @NotBlank
    String bio;
}