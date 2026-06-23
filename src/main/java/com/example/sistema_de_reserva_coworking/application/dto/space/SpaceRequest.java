package com.example.sistema_de_reserva_coworking.application.dto.space;

import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceRequest {
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "spaceType is required")
    private SpaceType spaceType;
    @NotNull(message = "maxCapacity is required")
    @Positive(message = "maxCapacity must be positive")
    @Max(value = 20, message = "maxCapacity is 20 ")
    private Integer maxCapacity;
    @NotBlank(message = "description is required")
    private String description;
}
