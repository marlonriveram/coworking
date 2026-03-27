package com.example.sistema_de_reserva_coworking.application.validator;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingRequest;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.exceptions.BadRequest;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;
import com.example.sistema_de_reserva_coworking.infrastructure.repository.projections.SpaceCapacity;
import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingValidator {

    private final AuthRepository authRepository;
    private final SpaceRepository spaceRepository;
    private final BookingRepository bookingRepository;

    public void Create(BookingRequest request) {
        validateBookingExists(request);
        validateUserExists(request);
        validateSpaceExists(request);
        validateMaxCapacity(request);
        validateBookingOccupied(request);
    }

    public Long getAll (CustomUserPrincipal principal, Long userId) {

        if (principal.getRole().equals(UserRole.ADMIN.name())) {
            if (userId == null) throw new BadRequest("UserId requerido para Admin");
            return userId;
        }

        return principal.getUserId();
    }


    private void validateBookingExists(BookingRequest request){

        CompoundKey id =  new CompoundKey(
                request.getUserId(),
                request.getSpaceId()
        );

        boolean existsBooking = bookingRepository.existsById(id);
        if (existsBooking) {
            throw new AlreadyExists("El Usuraio ya se encuentra registrado en esta clase");

        }

}

    private void validateBookingOccupied (BookingRequest request){
        boolean isOccupied = bookingRepository.existsBySpaceIdAndDateAndSlot(
                request.getSpaceId(),
                request.getDate(),
                request.getSlot()
        );

        if (isOccupied) {
            throw new AlreadyExists("Espacio no disponible en ese horario");
        }
    }

    private void validateUserExists(BookingRequest request){
        boolean exists = authRepository.existsById(request.getUserId());

        if (!exists) {
            throw new NotFound("Usuario no encontrado");
        }
    }

    private void validateSpaceExists(BookingRequest request){
        boolean exists = spaceRepository.existsById(request.getSpaceId());
        if (!exists) {
            throw new NotFound("Espacio no encontrado");
        }
    }

    private void validateMaxCapacity(BookingRequest request){
        SpaceCapacity maxCap = spaceRepository.findMaxCapacityById(request.getSpaceId())
                .orElseThrow(() -> new NotFound("Espacio no encontrado"));

        Integer capacidadReal = maxCap.getMaxCapacity();

        if (request.getAttendees() > capacidadReal) {
            throw new BadRequest("Capacidad excedida");
        }
    }
}

// CREAR CONTROLLER YA DE MOSTRAR OPCIONES DE HORARIOS Y MOSTRAR TODAS LAS RESERVAS