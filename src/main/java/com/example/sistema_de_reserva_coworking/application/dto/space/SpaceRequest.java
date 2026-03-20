package com.example.sistema_de_reserva_coworking.application.dto.space;

import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceRequest {
    @NotBlank
    private String name;
    @NotNull
    private SpaceType spaceType;
    @NotNull
    private Integer maxCapacity;
    @NotBlank
    private String description;
}
