package com.example.sistema_de_reserva_coworking.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private String message;
    private int status;
    private String error;
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL) // Solo aparece si no es null
    private List<String> subErrors;
}
