package com.example.sistema_de_reserva_coworking.domain.repository;


import com.example.sistema_de_reserva_coworking.domain.model.Space;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository {

    List<Space> findAll();

    Space save(Space space);

    Optional<Space> findById(Long id);

    void deleteById(Long id);

    Optional<Space> findByName(String name);

    boolean existsByName(String name);

}
