package com.onlinetutoring.usersservice.domain.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.usersservice.domain.entity.Subject}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
public class SubjectReadDto implements Serializable {
    String name;
    String description;
}