package com.example.sistema_de_reserva_coworking.application.service.booking;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingRequest;
import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.BookingMapper;
import com.example.sistema_de_reserva_coworking.application.validator.BookingValidator;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.exceptions.Unauthorized;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.CustomUserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingUpdate{

    private final BookingRepository bookingRepository;
    private final SpaceRepository spaceRepository;
    private final BookingValidator validator;

    public BookingResponse execute(
            CustomUserPrincipal principal,
            Long bookingId,
            BookingRequest request) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFound("Reserva no encontrada"));

        /*
        boolean isOwner = booking.getUser().getId().equals(principal.getUserId());
        boolean isAdmin = principal.getRole().equals(UserRole.ADMIN.name());

        if(!isOwner && !isAdmin){
            throw new Unauthorized("No tienes permiso para editar este reserva");
        }

         */

        validator.Updta(request,null,null);

        Space space = spaceRepository.getReferenceById(request.getSpaceId());

        booking.update(
                request.getDate(),
                request.getSlot(),
                request.getAttendees(),
                space
        );


        return BookingMapper.mapBookingToBookingResponse(bookingRepository.save(booking));

    }
}
