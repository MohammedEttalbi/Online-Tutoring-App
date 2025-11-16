package com.onlinetutoring.service_sessions.domain.dto.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


public class SessionCreateDto implements Serializable {

    @NotBlank
    @Size(max = 50)
    public String name;
    @Positive
    public double duration;

    // Optional linkage by ids; owner side requires schedule id
    public Long scheduleId;
    public List<Long> materialIds;
}

