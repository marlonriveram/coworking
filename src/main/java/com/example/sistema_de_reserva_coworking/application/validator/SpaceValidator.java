package com.example.sistema_de_reserva_coworking.application.validator;

import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceRequest;
import com.example.sistema_de_reserva_coworking.application.dto.space.SpaceResponse;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.exceptions.BadRequest;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceValidator {


    private final SpaceRepository spaceRepository;

    public void create (SpaceRequest request){

        validateExistsSpace(request);
        validateMaxCapacity(request);
    }

    private void validateExistsSpace(SpaceRequest request){
        boolean existsSpace = spaceRepository.existsByName(request.getName());

        if(existsSpace){
            throw  new AlreadyExists("Space already exists");
        }

    }

    private void validateMaxCapacity(SpaceRequest request){

        boolean isHall = request.getSpaceType().name().equals("HALL");
        boolean exceededCapacity = request.getMaxCapacity() > 1;

        if(isHall && exceededCapacity){
            throw new BadRequest("La pacacidad maxima de un Hall es 1");
        }
    }
}
