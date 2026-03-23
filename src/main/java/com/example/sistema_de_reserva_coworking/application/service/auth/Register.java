package com.example.sistema_de_reserva_coworking.application.service.auth;

import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterRequest;
import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.UserMapper;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Register {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse execute (RegisterRequest request) {

        boolean existsEmail = authRepository.existsByEmail(request.getEmail());
        if (existsEmail) {
            throw new AlreadyExists("Emial ya esta registrado");
        }

        String password = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .rol(UserRole.USER)
                .password(password)
                .build();

        return UserMapper.mapUserToUserResponse(authRepository.save(user));
    }
}
