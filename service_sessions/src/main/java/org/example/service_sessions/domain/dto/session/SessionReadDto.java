package org.example.service_sessions.domain.dto.session;

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
    private Long scheduleId;
    private List<Long> materialIds;
    private List<Long> bookingIds;
}

