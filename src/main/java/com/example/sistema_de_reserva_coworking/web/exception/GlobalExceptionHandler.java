package com.example.sistema_de_reserva_coworking.web.exception;

import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.exceptions.BadRequest;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.exceptions.Unauthorized;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Errores personaliazados

    @ExceptionHandler(AlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleAlreadyExists(AlreadyExists exception, HttpServletRequest request) {

        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.CONTINUE.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                request.getRequestURI(),
                null
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleUnauthorized(Unauthorized exception, HttpServletRequest request) {

        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                request.getRequestURI(),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleNotFound(NotFound exception, HttpServletRequest request) {
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                request.getRequestURI(),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handlerBadRequest(BadRequest exception, HttpServletRequest request) {
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getRequestURI(),
                null
        );

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }



    // Investigar como organizar este error

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleInvalidFormat(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String message = "Formato de datos inválido en el cuerpo de la solicitud";
        Throwable cause = ex.getMostSpecificCause();

        // Usamos la clase base MismatchedInputException que es el padre de todas las de Jackson
        if (cause instanceof com.fasterxml.jackson.databind.exc.MismatchedInputException mEx) {

            // 1. Intentamos obtener el nombre del campo fallido
            String fieldName = (mEx.getPath() != null && !mEx.getPath().isEmpty())
                    ? mEx.getPath().get(0).getFieldName()
                    : "desconocido";

            // 2. Si el tipo de destino es un Enum (aquí es donde estaba fallando)
            Class<?> targetType = mEx.getTargetType();
            if (targetType != null && targetType.isEnum()) {
                // Obtenemos los valores del Enum de forma dinámica
                Object[] enums = targetType.getEnumConstants();
                String acceptedValues = java.util.Arrays.toString(enums);

                message = String.format("El campo '%s' tiene un valor inválido. Valores permitidos: %s",
                        fieldName, acceptedValues);
            } else {
                // Error de tipo común (ej: String en un Long)
                String typeName = (targetType != null) ? targetType.getSimpleName() : "el tipo esperado";
                message = String.format("El campo '%s' tiene un formato incorrecto. Se esperaba un valor de tipo %s",
                        fieldName, typeName);
            }
        }

        ApiError error = new ApiError(
                message,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getRequestURI(),null
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




    //Capturar errores de validacion @Valid

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .toList();

        ApiError error = new ApiError(
                "Error en el contenido del body",
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getRequestURI(),
                null
        );

        error.setSubErrors(details);


        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    // Captura errores de BD

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = "Error de integridad de datos";

        // Intentamos extraer una causa más específica para saber si es un duplicado
        if (ex.getMostSpecificCause().getMessage().contains("Duplicate entry") ||
                ex.getMostSpecificCause().getMessage().contains("violates unique constraint")) {
            message = "Ya existe un registro con esos datos (valor duplicado)";
        }

        ApiError error = new ApiError(
                message,
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                request.getRequestURI(),
                null
        );


        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
