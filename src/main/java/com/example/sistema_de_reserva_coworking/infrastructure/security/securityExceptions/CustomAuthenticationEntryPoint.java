package com.example.sistema_de_reserva_coworking.infrastructure.security.securityExceptions;

import com.example.sistema_de_reserva_coworking.web.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// CUANDO SE INTENTA ACCEDER SIN TENER UN TOKEN O EL TOKEN NO ES VALIDO
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(@NonNull HttpServletRequest request,
                         @NonNull HttpServletResponse response,
                         @NonNull AuthenticationException authException)
            throws IOException{


        ApiError error = new ApiError(
                "Token inválido o ausente. Debe iniciar sesión.",
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                request.getRequestURI(),
                null
        );


       // seteo status code en la response
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // seteo contenido indicando que es un json en la response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Convertimos el objeto a JSON usando ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(error);

        response.getWriter().write(jsonResponse);
    }
}
