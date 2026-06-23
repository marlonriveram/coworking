package com.example.sistema_de_reserva_coworking.application.dto.auth;

import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private Long id;
    private String username;
    private String email;
    private UserRole rol;
}
