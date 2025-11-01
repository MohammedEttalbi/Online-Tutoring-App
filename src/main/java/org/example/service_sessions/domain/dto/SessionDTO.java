package org.example.service_sessions.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.service_sessions.domain.entity.Session;

import java.io.Serializable;

/**
 * DTO for {@link Session}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO implements Serializable {

    @NotBlank
    @Size(max = 50)
    private String name;

    @Positive
    private double duration;
}

