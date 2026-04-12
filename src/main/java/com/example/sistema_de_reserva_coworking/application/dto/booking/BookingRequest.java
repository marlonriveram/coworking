package com.example.sistema_de_reserva_coworking.application.dto.booking;

import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {
    @NotNull(message = "spaceType is required")
    private Long spaceId;
    @NotNull(message = "date is requiered")
    @FutureOrPresent(message = "the date can not be in the past")
    private LocalDate date;
    @NotNull(message = "slot is required")
    private ReservationSlot slot;
    private int attendees;
}
