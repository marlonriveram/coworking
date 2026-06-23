package com.example.sistema_de_reserva_coworking.application.dto.space;

import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@RequiredArgsConstructor
public class AvailableSpaceRequest {
    @NotNull(message = "date is requered")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "slot is required")
    private ReservationSlot slot;
}
