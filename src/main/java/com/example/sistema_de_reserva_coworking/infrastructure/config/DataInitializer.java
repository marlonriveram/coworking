package com.example.sistema_de_reserva_coworking.infrastructure.config;

import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.email}")
    private String email;

    @Override
    public void run(String @NonNull ... args) throws Exception {

        boolean existsAdmin = authRepository.existsByEmail(email);

        if(!existsAdmin){
            User admin = User.builder()
                    .username("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode(password))
                    .rol(UserRole.ADMIN)
                    .build();

            authRepository.save(admin);

            System.out.println("✅ Admin user created");
        }


    }
}
