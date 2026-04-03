package com.example.sistema_de_reserva_coworking.application.dto.space;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableSpaceResponse {
    private Long id;
    private String name;
    private String description;
}

