package org.example.service_sessions.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.service_sessions.domain.entity.Schedule}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO implements Serializable {

    @NotNull
    private LocalDateTime dateTime;
}

