package com.example.sistema_de_reserva_coworking.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAndSpace {
    private Long userId;
    private Long  spaceId;
}
