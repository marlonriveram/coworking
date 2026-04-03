package com.example.sistema_de_reserva_coworking.domain.repository;

import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;

import java.util.Optional;

public interface AuthRepository {

    User save (User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    User getReferenceById (Long id);

}
