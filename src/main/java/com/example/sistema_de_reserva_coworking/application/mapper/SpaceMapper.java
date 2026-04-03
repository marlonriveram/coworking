package com.example.sistema_de_reserva_coworking.application.mapper;


import com.example.sistema_de_reserva_coworking.application.dto.space.AvailableSpaceResponse;
import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;

public class SpaceMapper {

    public static Space mapSpaceEntityToSpace(SpaceEntity spaceEntity) {
        if (spaceEntity == null) return null;

        return Space.builder()
                .id(spaceEntity.getId())
                .name(spaceEntity.getName())
                .spaceType(spaceEntity.getSpaceType())
                .maxCapacity(spaceEntity.getMaxCapacity())
                .description(spaceEntity.getDescription())
                .build();
    }

    public static SpaceEntity mapSpaceToSpaceEntity(Space space) {
        if (space == null) return null;

        return SpaceEntity.builder()
                .id(space.getId())
                .name(space.getName())
                .spaceType(space.getSpaceType())
                .maxCapacity(space.getMaxCapacity())
                .description(space.getDescription())
                .build();
    }

    public static SpaceResponse mapSpaceToSpaceResponse(Space space) {
        if (space == null) return null;

        return SpaceResponse.builder()
                .id(space.getId())
                .name(space.getName())
                .spaceType(space.getSpaceType())
                .maxCapacity(space.getMaxCapacity())
                .description(space.getDescription())
                .build();
    }

    public static AvailableSpaceResponse mapSpaceToAvailableSpaceResponse(Space space) {
        if (space == null) return null;

        return AvailableSpaceResponse.builder()
                .id(space.getId())
                .name(space.getName())
                .description(space.getDescription())
                .build();
    }
}
