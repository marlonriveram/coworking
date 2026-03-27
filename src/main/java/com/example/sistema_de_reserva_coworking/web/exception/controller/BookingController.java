package com.example.sistema_de_reserva_coworking.web.exception.controller;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingRequest;
import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.application.service.booking.BookingCreate;
import com.example.sistema_de_reserva_coworking.application.service.booking.BookingGetAll;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.CustomUserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingCreate create;
    private final BookingGetAll getAll;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request){

        BookingResponse response = create.execute(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings(
            @RequestParam(name = "userId", required = false) Long userId,
            @AuthenticationPrincipal CustomUserPrincipal principal
    ){
        List<BookingResponse> bookings = getAll.execute(principal,userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

}
