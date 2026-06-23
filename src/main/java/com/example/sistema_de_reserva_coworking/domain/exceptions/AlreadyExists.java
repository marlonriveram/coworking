package com.example.sistema_de_reserva_coworking.domain.exceptions;

public class AlreadyExists extends RuntimeException {
    public AlreadyExists(String message) {
        super(message);
    }
}
