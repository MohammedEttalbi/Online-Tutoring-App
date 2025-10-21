package com.onlinetutoring.usersservice.domain.dto.qualification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Qualification}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
public class QualificationReadDto implements Serializable {
    String title;
    String institution;
}