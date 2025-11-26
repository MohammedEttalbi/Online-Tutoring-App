package com.onlinetutoring.usersservice.domain.dto.qualification;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Qualification}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QualificationReadDto implements Serializable {
    Long id;
    String title;
    String institution;
}