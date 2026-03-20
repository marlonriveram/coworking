package com.example.sistema_de_reserva_coworking.domain.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Space {

    private Long id;
    private String name;
    private SpaceType spaceType;
    private Integer maxCapacity;
    private String description;

    public void update (String name, Integer maxCapacity, String description, SpaceType spaceType) {

        if(name != null) {
            this.name = name;
        }
        if(spaceType != null) {
            this.spaceType = spaceType;
        }

        if(description != null) {
            this.description = description;
        }

        if(maxCapacity != null ) {
            this.maxCapacity = maxCapacity;
        }
    }
}
