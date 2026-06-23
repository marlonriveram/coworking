package com.example.sistema_de_reserva_coworking.application.service.space;

import com.example.sistema_de_reserva_coworking.application.dto.space.AvailableSpaceRequest;
import com.example.sistema_de_reserva_coworking.application.dto.space.AvailableSpaceResponse;
import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.SpaceMapper;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SpaceGetAll {
    private final SpaceRepository spaceRepository;
    private final BookingRepository bookingRepository;

    public List<AvailableSpaceResponse> executed (AvailableSpaceRequest request) {

       List<AvailableSpaceResponse> spaces =  spaceRepository.findAll()
               .stream()
               .map(SpaceMapper::mapSpaceToAvailableSpaceResponse)
               .toList();

       List<AvailableSpaceResponse> avaibleSpaces = spaces.stream().filter(space ->
               !bookingRepository.existsBySpaceIdAndDateAndSlot(space.getId(),request.getDate(),request.getSlot())
               ).toList();

        return avaibleSpaces;
    }
}
