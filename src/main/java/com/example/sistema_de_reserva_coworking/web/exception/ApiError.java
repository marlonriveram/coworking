package com.example.sistema_de_reserva_coworking.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {
    private String message;
    private int status;
    private String error;
    private String path;

}
