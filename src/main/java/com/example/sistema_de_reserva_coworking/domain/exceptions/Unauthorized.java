package com.example.sistema_de_reserva_coworking.domain.exceptions;

public class Unauthorized extends RuntimeException {
    public Unauthorized(String message) {
        super(message);
    }
}
