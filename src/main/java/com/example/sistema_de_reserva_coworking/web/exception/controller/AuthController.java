package com.example.sistema_de_reserva_coworking.web.exception.controller;

import com.example.sistema_de_reserva_coworking.application.dto.auth.LoginRequest;
import com.example.sistema_de_reserva_coworking.application.dto.auth.LoginResponse;
import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterRequest;
import com.example.sistema_de_reserva_coworking.application.dto.auth.RegisterResponse;
import com.example.sistema_de_reserva_coworking.application.service.auth.Login;
import com.example.sistema_de_reserva_coworking.application.service.auth.Register;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final Login login;
    private final Register register;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(register.execute(request));
    }


    @PostMapping
    public ResponseEntity<LoginResponse> hello(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(login.login(request));
    }
}
