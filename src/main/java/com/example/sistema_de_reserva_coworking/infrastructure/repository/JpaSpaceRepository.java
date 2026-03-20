package com.example.sistema_de_reserva_coworking.infrastructure.repository;

import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSpaceRepository extends JpaRepository<SpaceEntity, Long> {

    Optional<SpaceEntity> findByName(String name);

    boolean existsByName(String name);
}
