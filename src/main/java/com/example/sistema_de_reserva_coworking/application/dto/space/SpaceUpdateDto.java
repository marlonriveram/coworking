package com.example.sistema_de_reserva_coworking.application.dto.space;

import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceUpdateDto {
    private String name;
    private SpaceType spaceType;
    private String description;
    private Integer maxCapacity;
}
