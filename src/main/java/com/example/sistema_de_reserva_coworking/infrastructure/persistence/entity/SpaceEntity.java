package com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity;

import com.example.sistema_de_reserva_coworking.domain.model.SpaceStatus;
import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spaces")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private SpaceType spaceType;
    private Integer maxCapacity;
    private String description;

    @OneToMany(mappedBy = "space")
    private List<BookingEntity> bookings = new ArrayList<>();
}

