package com.onlinetutoring.evaluationservice.domain.dto.Progress;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onlinetutoring.evaluationservice.domain.entity.Progress}
 */
@Value
public class ProgressReadDto implements Serializable {
    Long id;
    String goal;
    String status;
}