package com.example.sistema_de_reserva_coworking.domain.model;

import lombok.*;

import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    private User user;
    private Space space;
    private LocalTime startTime;
    private LocalTime endTime;
    private int attendees;
}
