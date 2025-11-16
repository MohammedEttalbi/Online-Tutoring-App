package com.onlinetutoring.service_sessions.domain.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDeleteDto implements Serializable {

    @NotNull
    private Long id;
}

