package com.example.sistema_de_reserva_coworking.application.service.booking;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.BookingMapper;
import com.example.sistema_de_reserva_coworking.application.validator.BookingValidator;
import com.example.sistema_de_reserva_coworking.domain.exceptions.BadRequest;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingGetAll {

    private final BookingRepository bookingRepository;
    private final BookingValidator validator;

    public List<BookingResponse> execute(CustomUserPrincipal principal, Long userId) {

      Long idToSearch = validator.getAll(principal, userId);

        return bookingRepository.findByUserId(idToSearch)
                .stream().map(BookingMapper::mapBookingToBookingResponse)
                .toList();
    }
}
