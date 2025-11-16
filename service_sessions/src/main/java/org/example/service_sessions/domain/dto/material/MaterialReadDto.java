package org.example.service_sessions.domain.dto.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialReadDto implements Serializable {

    private Long id;
    private String name;
    private String type;
}

