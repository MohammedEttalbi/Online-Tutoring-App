package com.onlinetutoring.usersservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Subject}
 */
@Value
public class SubjectDto implements Serializable {
    @NotNull
    @Size(max = 80)
    @NotEmpty
    @NotBlank
    String name;
    @NotNull
    @Size(max = 80)
    @NotEmpty
    @NotBlank
    String description;
}