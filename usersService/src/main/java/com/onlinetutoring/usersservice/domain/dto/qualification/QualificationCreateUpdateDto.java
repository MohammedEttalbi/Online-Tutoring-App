package com.onlinetutoring.usersservice.domain.dto.qualification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Qualification}
 */
@Getter
@Setter
@AllArgsConstructor
public class QualificationCreateUpdateDto implements Serializable {
    @NotNull
    @Size(max = 80)
    @NotEmpty
    @NotBlank
    String title;
    @NotNull
    @Size
    @NotEmpty
    @NotBlank
    String institution;
}