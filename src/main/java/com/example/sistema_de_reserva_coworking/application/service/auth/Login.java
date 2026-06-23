    package com.example.sistema_de_reserva_coworking.application.service.auth;

    import com.example.sistema_de_reserva_coworking.application.dto.auth.LoginRequest;
    import com.example.sistema_de_reserva_coworking.application.dto.auth.LoginResponse;
    import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterRequest;
    import com.example.sistema_de_reserva_coworking.application.mapper.UserMapper;
    import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
    import com.example.sistema_de_reserva_coworking.domain.model.User;
    import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
    import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.UserEntity;
    import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.UserDetailsAdapter;
    import com.example.sistema_de_reserva_coworking.infrastructure.service.Jwt;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.BadCredentialsException;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class Login {

        private final AuthRepository authRepository;
        private final AuthenticationManager authenticationManager;
        private final Jwt jwt;

        public LoginResponse login(LoginRequest request) {

            try{
                //Autenticacion del usuario
                Authentication auth =  authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

                // 1. Casteamos al ADAPTADOR que tú mismo creaste
                UserDetailsAdapter adapter = (UserDetailsAdapter) auth.getPrincipal();

                // 2. Extraemos la ENTIDAD que vive dentro del adaptador
                assert adapter != null;
                UserEntity userEntity = adapter.getUserEntity();

                assert userEntity != null;
                String token = jwt.generateToken(UserMapper.mapUserEntityToUser(userEntity));

                return new LoginResponse(token);
            }catch (BadCredentialsException e) {
                throw new BadCredentialsException("Invalid email or password");
            }


        }

    }
