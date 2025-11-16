package com.onlinetutoring.evaluationservice.domain.dto.Progress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressCreateUpdateDto {
    String goal;
    String status;
    Long reviewId;
}

