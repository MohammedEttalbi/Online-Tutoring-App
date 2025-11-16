package org.example.service_sessions.domain.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateDto implements Serializable {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private Long sessionId;
}

