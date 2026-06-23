package com.example.sistema_de_reserva_coworking.infrastructure.repository.auth;

import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAuthRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
