package com.example.sistema_de_reserva_coworking.infrastructure.config.persistence.entity;

import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spaces")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SpaceType spaceType;
    private int maxCapacity;
    private String description;

    @OneToMany(mappedBy = "space")
    private List<BookingEntity> bookings = new ArrayList<>();
}

