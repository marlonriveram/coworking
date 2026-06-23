package com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity;

import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    //@MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
   // @MapsId("spaceId")
    @JoinColumn(name = "space_id")
    private SpaceEntity space;

    @Enumerated(EnumType.STRING)
    private ReservationSlot slot;
    private LocalDate date;
    private int attendees;

}
