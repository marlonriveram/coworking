package com.example.sistema_de_reserva_coworking.application.service.space;

import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceRequest;
import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.SpaceMapper;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.SpaceType;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Update {
    private final SpaceRepository spaceRepository;

    public SpaceResponse executed (SpaceRequest request, Long id) {

        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new NotFound("El espacio con ese nombre no existe"));

        if(space.getName().equals(request.getName())){

            throw new AlreadyExists("Ese nombre existe ya existe");
        }

        if(space.getSpaceType().name() == SpaceType.HALL.name()){
            request.setMaxCapacity(1);
        }

        space.update(

                request.getName(),
                request.getMaxCapacity(),
                request.getDescription(),
                request.getSpaceType()
        );

        System.out.println("dfdfdf" + space.getId());

        return SpaceMapper.mapSpaceToSpaceResponse(spaceRepository.save(space));
    }
}
