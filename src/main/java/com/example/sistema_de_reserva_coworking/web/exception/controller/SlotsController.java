package com.example.sistema_de_reserva_coworking.web.exception.controller;

import com.example.sistema_de_reserva_coworking.application.dto.slot.SlotResponse;
import com.example.sistema_de_reserva_coworking.application.service.Slots.GetAllSlots;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/slots")
@RequiredArgsConstructor
public class SlotsController {

     private final GetAllSlots getAllSlots;
     @GetMapping

    public ResponseEntity<List<SlotResponse>> getSlots () {
        List<SlotResponse> slots = getAllSlots.getAllSlots();
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }
}
