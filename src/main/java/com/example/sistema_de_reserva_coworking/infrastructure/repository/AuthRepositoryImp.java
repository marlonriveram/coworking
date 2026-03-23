package com.example.sistema_de_reserva_coworking.infrastructure.repository;

import com.example.sistema_de_reserva_coworking.application.mapper.UserMapper;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImp implements AuthRepository {
    private final JpaAuthRepository jpaAuthRepository;

    @Override
    public User save(User user) {

        UserEntity entity = UserMapper.mapUsertoUserEntity(user);

        return UserMapper.mapUserEntityToUser(jpaAuthRepository.save(entity));
    }

    @Override
    public Optional<User> findById(Long id) {

        return jpaAuthRepository.findById(id).map(UserMapper::mapUserEntityToUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return jpaAuthRepository.findByEmail(email).map(UserMapper::mapUserEntityToUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaAuthRepository.existsByEmail(email);
    }
}
