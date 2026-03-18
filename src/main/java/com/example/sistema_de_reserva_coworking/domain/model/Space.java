package com.example.sistema_de_reserva_coworking.domain.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Space {

    private Long id;
    private SpaceType spaceType;
    private int maxCapacity;
    private String description;
}
