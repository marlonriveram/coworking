package com.example.sistema_de_reserva_coworking.domain.repository;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.infrastructure.repository.projections.SpaceCapacity;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository {

    List<Space> findAll();

    Space save(Space space);

    List<Space> saveAll(List<Space> spaces);

    Optional<Space> findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String name);

    // Obtener un proxy de la entidad que solo trae el id
    Space getReferenceById (Long id);

    // Obtener un atributo en partucular no toda la entidad
    Optional<SpaceCapacity> findMaxCapacityById(Long id);

}
