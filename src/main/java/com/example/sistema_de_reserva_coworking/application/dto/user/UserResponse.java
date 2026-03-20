package com.example.sistema_de_reserva_coworking.application.dto.user;

import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String username;
    private String email;
    private UserRole rol;
}
