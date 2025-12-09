package com.onlinetutoring.service_sessions.domain.dto.session;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionUpdateDto implements Serializable {

    @NotNull
    private Long id;

    @Size(max = 50)
    private String name;

    @Positive
    private Double duration;
    private Long tutorId;
    private Long scheduleId;
    private List<Long> materialIds;
}
