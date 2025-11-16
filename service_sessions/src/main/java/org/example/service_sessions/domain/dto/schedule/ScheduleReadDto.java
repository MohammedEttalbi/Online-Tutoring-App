package org.example.service_sessions.domain.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleReadDto implements Serializable {

    private Long id;
    private LocalDateTime dateTime;
}

