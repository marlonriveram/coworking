package com.example.sistema_de_reserva_coworking.infrastructure.config.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity {

    @EmbeddedId
    private CompoundKey compoundKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private SpaceEntity space;

    private LocalTime startTime;
    private LocalTime endTime;
    private int attendees;

}
