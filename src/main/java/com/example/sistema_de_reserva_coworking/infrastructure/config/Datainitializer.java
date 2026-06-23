package com.example.sistema_de_reserva_coworking.infrastructure.config;

import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Datainitializer implements CommandLineRunner {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpaceRepository spaceRepository;

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

        List<Space> initialSpaces = List.of(
                // --- SALAS (HALL) ---
                Space.builder()
                        .name("Sala Alpha")
                        .description("Piso 1, con TV y Tablero")
                        .maxCapacity(10)
                        .spaceType(SpaceType.HALL)
                        .build(),
                Space.builder()
                        .name("Sala Beta")
                        .description("Piso 1, Ideal para entrevistas")
                        .maxCapacity(4)
                        .spaceType(SpaceType.HALL)
                        .build(),

                // --- ESCRITORIOS (DESK) ---
                Space.builder()
                        .name("Escritorio 101")
                        .description("Piso 2, zona silenciosa")
                        .maxCapacity(1)
                        .spaceType(SpaceType.DESK)
                        .build(),
                Space.builder()
                        .name("Escritorio 102")
                        .description("Piso 2, cerca a la ventana")
                        .maxCapacity(1)
                        .spaceType(SpaceType.DESK)
                        .build(),
                Space.builder()
                        .name("Cabina Privada")
                        .description("Piso 1, insonorizada para llamadas")
                        .maxCapacity(1)
                        .spaceType(SpaceType.DESK)
                        .build()
        );

        spaceRepository.saveAll(initialSpaces);
        System.out.println("🏢 5 Espacios de coworking creados con éxito.");

    }
}
