package com.example.sistema_de_reserva_coworking.application.dto.booking;

import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private String nameUser;
    private String nameSpace;
    private LocalDate date;
    private ReservationSlot slot;
    private int attendees;
}
