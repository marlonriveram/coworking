package com.example.sistema_de_reserva_coworking.application.service.booking;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.application.validator.BookingValidator;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.exceptions.Unauthorized;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.CustomUserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingDelete {

    private final BookingRepository bookingRepository;
    private final BookingValidator validator;

    public void execute(CustomUserPrincipal principal,Long bookingId){

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFound("Reserva no encontrada"));

        validator.delete(booking, principal);
/*
        boolean isOwner = booking.getUser().getId().equals(principal.getUserId());
        boolean isAdmin = principal.getRole().equals(UserRole.ADMIN.name());

        if(!isOwner && !isAdmin){
            throw new Unauthorized("No tienes acceso para eliminar la recerva");
        }

 */
        bookingRepository.deleteById(bookingId);
    }
}
