package com.example.sistema_de_reserva_coworking.domain.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
