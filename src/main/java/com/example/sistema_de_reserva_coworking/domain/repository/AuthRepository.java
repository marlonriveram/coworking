package com.example.sistema_de_reserva_coworking.domain.repository;

import com.example.sistema_de_reserva_coworking.domain.model.User;

import java.util.Optional;

public interface AuthRepository {

    User save (User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
