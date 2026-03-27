package com.example.sistema_de_reserva_coworking.application.service.booking;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingRequest;
import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.application.mapper.BookingMapper;
import com.example.sistema_de_reserva_coworking.application.validator.BookingValidator;
import com.example.sistema_de_reserva_coworking.domain.exceptions.AlreadyExists;
import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingCreate {
    private final BookingRepository bookingRepository;
    private final AuthRepository authRepository;
    private final SpaceRepository spaceRepository;
    private final BookingValidator validator;

    // PROBAR SI CREA LA BOOKING, SI LO HACE TENER EN CUENTA QUE SE BEBEN VALIDAR REGLAS DE NEGOCION QUE
    // AUN NO SE HAN TOMADO EN CUENTA

    public BookingResponse execute (BookingRequest request) {

       validator.Create(request);

        User user = authRepository.getReferenceById(request.getUserId());
        Space space = spaceRepository.getReferenceById(request.getSpaceId());

        Booking booking = Booking.builder()
                .user(user)
                .space(space)
                .date(request.getDate())
                .slot(request.getSlot())
                .attendees(request.getAttendees())
                .build();


        return BookingMapper.mapBookingToBookingResponse(bookingRepository.save(booking));
    }
}
