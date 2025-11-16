package com.onlinetutoring.service_sessions.domain.dto.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialCreateDto implements Serializable {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String type;
}

