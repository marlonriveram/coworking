package com.example.sistema_de_reserva_coworking.web.exception.controller;

import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceRequest;
import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.application.service.space.Create;
import com.example.sistema_de_reserva_coworking.application.service.space.Delete;
import com.example.sistema_de_reserva_coworking.application.service.space.GetAll;
import com.example.sistema_de_reserva_coworking.application.service.space.Update;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
@Slf4j
public class SpaceController {

    private final Create create;
    private final GetAll getAll;
    private final Update update;
    private final Delete delete;


    @PostMapping
    public ResponseEntity<SpaceResponse> create(@Valid @RequestBody SpaceRequest space) {
        return ResponseEntity.ok(create.executed(space));
    }

    @GetMapping
    public ResponseEntity<List<SpaceResponse>> getAll() {
        return ResponseEntity.ok(getAll.executed());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceResponse> update(@RequestBody SpaceRequest space, @PathVariable Long id) {
        return ResponseEntity.ok(update.executed(space, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(delete.executed(id));
    }
}
