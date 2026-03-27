package com.example.sistema_de_reserva_coworking.application.service.space;

import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.SpaceMapper;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceGetAll {
    private final SpaceRepository spaceRepository;

    public List<SpaceResponse> executed () {
        return spaceRepository.findAll().stream().map(SpaceMapper::mapSpaceToSpaceResponse).toList();
    }
}
