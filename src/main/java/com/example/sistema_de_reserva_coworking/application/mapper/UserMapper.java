package com.example.sistema_de_reserva_coworking.application.mapper;

import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterResponse;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity mapUsertoUserEntity(User user) {
        if (user == null) return null;

        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .rol(user.getRol())
                .build();
    }

    public static User mapUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null) return null;

        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .rol(userEntity.getRol())
                .build();
    }

    public static RegisterResponse mapUserToUserResponse(User user) {
        if (user == null) return null;

        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .rol(user.getRol())
                .build();
    }
}
