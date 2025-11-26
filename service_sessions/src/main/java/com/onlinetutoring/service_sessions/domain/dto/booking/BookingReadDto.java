package com.onlinetutoring.service_sessions.domain.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingReadDto implements Serializable {

    private Long id;
    private LocalDateTime dateTime;
    private Long sessionId;
    private String studentName;
}

