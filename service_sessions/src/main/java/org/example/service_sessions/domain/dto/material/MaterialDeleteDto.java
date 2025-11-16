package org.example.service_sessions.domain.dto.material;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDeleteDto implements Serializable {

    @NotNull
    private Long id;
}

