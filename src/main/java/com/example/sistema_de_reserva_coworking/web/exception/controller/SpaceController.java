package com.example.sistema_de_reserva_coworking.web.exception.controller;

import com.example.sistema_de_reserva_coworking.application.dto.space.*;
import com.example.sistema_de_reserva_coworking.application.service.space.SpaceCreate;
import com.example.sistema_de_reserva_coworking.application.service.space.SpaceDelete;
import com.example.sistema_de_reserva_coworking.application.service.space.SpaceGetAll;
import com.example.sistema_de_reserva_coworking.application.service.space.SpaceUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
@Slf4j
public class SpaceController {

    private final SpaceCreate create;
    private final SpaceGetAll getAll;
    private final SpaceUpdate update;
    private final SpaceDelete delete;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SpaceResponse> create(@Valid @RequestBody SpaceRequest space) {
        SpaceResponse response = create.executed(space);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SpaceResponse> update(@Valid @RequestBody SpaceUpdateDto space, @PathVariable Long id) {
        return ResponseEntity.ok(update.executed(space, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        delete.executed(id);
        return ResponseEntity.noContent().build();
    }


    // ENDPOINT PARA VER ESPACIOS DISPONIBLES
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/available")
    public ResponseEntity<List<AvailableSpaceResponse>> getAvailableSpaces(@Valid @RequestBody AvailableSpaceRequest request) {

        return  ResponseEntity.ok(getAll.executed(request));
    }


}
