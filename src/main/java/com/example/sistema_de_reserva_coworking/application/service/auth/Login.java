    package com.example.sistema_de_reserva_coworking.application.service.auth;

    import com.example.sistema_de_reserva_coworking.application.dto.auth.LoginRequest;
    import com.example.sistema_de_reserva_coworking.application.dto.auth.LoginResponse;
    import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterRequest;
    import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
    import com.example.sistema_de_reserva_coworking.domain.model.User;
    import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
    import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.UserEntity;
    import com.example.sistema_de_reserva_coworking.infrastructure.service.Jwt;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class Login {

        private final AuthRepository authRepository;
        private final AuthenticationManager authenticationManager;
        private final Jwt jwt;

        public LoginResponse login(LoginRequest request) {

            //Autenticacion del usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = authRepository.findByEmail(request.getEmail())
                            .orElseThrow(() -> new NotFound("User not found"));

            String token = jwt.generateToken(user);

            return new LoginResponse(token);

        }

    }
