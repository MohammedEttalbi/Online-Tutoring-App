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
public class BookingUpdateDto implements Serializable {

    @NotNull
    private Long id;

    private LocalDateTime dateTime;

    private Long sessionId;
}

