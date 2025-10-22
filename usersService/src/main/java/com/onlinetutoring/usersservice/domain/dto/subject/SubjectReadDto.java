package com.onlinetutoring.usersservice.domain.dto.subject;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Subject}
 */
@Value
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SubjectReadDto implements Serializable {
    String name;
    String description;
}