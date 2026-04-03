package com.example.sistema_de_reserva_coworking.application.validator;


import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingRequest;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.exceptions.BadRequest;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.exceptions.Unauthorized;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
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
        validateSpaceExists(request);
        validateMaxCapacity(request);
        validateBookingOccupied(request);
    }

    public void Updta(BookingRequest request, CustomUserPrincipal principal,Booking booking) {
        validateBookingOccupied(request);
        if(principal == null && booking == null) {
            validateMaxCapacity(request);
        }

        if(principal != null && booking != null) {
            validateIsOwnerOrAdmin(booking,principal);
        }

    }

    public Long getAll (CustomUserPrincipal principal, Long userId) {

        if (principal.getRole().equals(UserRole.ADMIN.name())) {
            if (userId == null) throw new BadRequest("UserId requerido para Admin");
            return userId;
        }

        return principal.getUserId();
    }

    public void delete(Booking booking,CustomUserPrincipal principal) {
       validateIsOwnerOrAdmin(booking, principal);
    }

    private void validateIsOwnerOrAdmin(Booking booking,CustomUserPrincipal principal) {
        boolean isOwner = booking.getUser().getId().equals(principal.getUserId());
        boolean isAdmin = principal.getRole().equals(UserRole.ADMIN.name());

        if(!isOwner && !isAdmin){
            throw new Unauthorized("No tienes acceso para eliminar la recerva");
        }
    }

    private void validateBookingOccupied (BookingRequest request){
        boolean isOccupied = bookingRepository.existsBySpaceIdAndDateAndSlot(
                request.getSpaceId(),
                request.getDate(),
                request.getSlot()
        );

        if (isOccupied) {
            throw new AlreadyExists("Espacio no disponible en esa fecha a esa hora");
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

