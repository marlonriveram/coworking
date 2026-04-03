package com.example.sistema_de_reserva_coworking.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    private Long id;
    private User user;
    private Space space;
    private LocalDate date;
    private ReservationSlot slot;
    private Integer attendees;


    public void update(LocalDate data,ReservationSlot slot, Integer attendees, Space space) {

        if(data != null) {
            this.date = data;
        }

        if(slot != null) {
            this.slot = slot;
        }

        if(attendees !=null) {
            this.attendees = attendees;
        }

        if(space != null) {
            this.space = space;
        }
    }
}
