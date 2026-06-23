package com.example.sistema_de_reserva_coworking.application.dto.slot;



public record SlotResponse(
        String name,
        String startTime,
        String endTime
) {}
