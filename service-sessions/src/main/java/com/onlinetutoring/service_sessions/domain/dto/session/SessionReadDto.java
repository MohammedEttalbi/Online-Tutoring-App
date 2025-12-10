package com.onlinetutoring.service_sessions.domain.dto.session;

import com.onlinetutoring.service_sessions.domain.dto.booking.BookingReadDto;
import com.onlinetutoring.service_sessions.domain.dto.material.MaterialReadDto;
import com.onlinetutoring.service_sessions.domain.dto.schedule.ScheduleReadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionReadDto implements Serializable {

    private Long id;
    private String name;
    private double duration;
    private Long tutorId;

    // Nested DTOs for related entities
    private ScheduleReadDto schedule;
    private List<MaterialReadDto> materials;
    private List<BookingReadDto> bookings;
}
