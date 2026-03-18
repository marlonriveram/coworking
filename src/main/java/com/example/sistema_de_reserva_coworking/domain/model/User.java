package com.example.sistema_de_reserva_coworking.domain.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole rol;
}
