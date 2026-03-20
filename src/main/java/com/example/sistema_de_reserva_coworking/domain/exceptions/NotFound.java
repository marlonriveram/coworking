package com.example.sistema_de_reserva_coworking.domain.exceptions;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
