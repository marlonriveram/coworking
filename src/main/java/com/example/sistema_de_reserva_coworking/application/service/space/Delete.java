package com.example.sistema_de_reserva_coworking.application.service.space;

import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Delete {
    private final SpaceRepository spaceRepository;

    public String executed (Long id) {

        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new NotFound("El espacio con ese nombre no existe"));


        spaceRepository.deleteById(id);

        return "El espacio con el " + id + "fue eliminado Correctamente";
    }
}
