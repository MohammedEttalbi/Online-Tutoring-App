package com.onlinetutoring.service_sessions.domain.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateDto implements Serializable {

    @NotNull
    private Long id;
    private LocalDateTime dateTime;
}

