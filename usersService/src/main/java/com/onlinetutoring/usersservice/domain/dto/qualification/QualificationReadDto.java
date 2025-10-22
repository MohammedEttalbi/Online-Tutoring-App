package com.onlinetutoring.usersservice.domain.dto.qualification;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Qualification}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QualificationReadDto implements Serializable {
    String title;
    String institution;
}