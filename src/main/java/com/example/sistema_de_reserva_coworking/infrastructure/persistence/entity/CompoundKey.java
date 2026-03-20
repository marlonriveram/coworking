package com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class CompoundKey implements Serializable {
    private Long userId;
    private Long  spaceId;
}
