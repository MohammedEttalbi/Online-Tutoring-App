package com.onlinetutoring.service_sessions.domain.dto.material;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUpdateDto implements Serializable {

    @NotNull
    private Long id;

    @Size(max = 50)
    private String name;

    private String type;
}

