package com.example.sistema_de_reserva_coworking.infrastructure.repository.space;

import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;
import com.example.sistema_de_reserva_coworking.infrastructure.repository.projections.SpaceCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaSpaceRepository extends JpaRepository<SpaceEntity, Long> {

    Optional<SpaceEntity> findByName(String name);

    boolean existsById(Long id);

    boolean existsByName(String name);

    Optional<SpaceCapacity> findMaxCapacityById(Long id);
}
