package com.example.sistema_de_reserva_coworking.application.service.space;

import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceRequest;
import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.SpaceMapper;
import com.example.sistema_de_reserva_coworking.application.validator.SpaceValidator;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.SpaceStatus;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SpaceCreate {

    private final SpaceRepository spaceRepository;
    private final SpaceValidator validator;

    public SpaceResponse executed (SpaceRequest request) {

        /*
            boolean existsSpace = spaceRepository.existsByName(request.getName());

            if(existsSpace){
                throw  new AlreadyExists("Space already exists");
            }

         */
        validator.create(request);

        Space space = Space.builder()
                .name(request.getName())
                .spaceType(request.getSpaceType())
                .maxCapacity(request.getMaxCapacity())
                .description(request.getDescription())
                .build();

      return SpaceMapper.mapSpaceToSpaceResponse(spaceRepository.save(space));
    }

}
