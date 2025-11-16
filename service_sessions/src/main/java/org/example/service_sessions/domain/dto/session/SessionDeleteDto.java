package org.example.service_sessions.domain.dto.session;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDeleteDto implements Serializable {

    @NotNull
    private Long id;
}

