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
    public SpaceResponse create(@Valid @RequestBody SpaceRequest space) {
        return create.executed(space);
    }

    @GetMapping
    public List<SpaceResponse> getAll() {
        return getAll.executed();
    }

    @PutMapping("/{id}")
    public SpaceResponse update(@RequestBody SpaceRequest space, @PathVariable Long id) {
        return update.executed(space,id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return delete.executed(id);
    }
}
