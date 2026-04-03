package com.example.sistema_de_reserva_coworking.web.exception.controller;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingRequest;
import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.application.service.booking.BookingCreate;
import com.example.sistema_de_reserva_coworking.application.service.booking.BookingDelete;
import com.example.sistema_de_reserva_coworking.application.service.booking.BookingGetAll;
import com.example.sistema_de_reserva_coworking.application.service.booking.BookingUpdate;
import com.example.sistema_de_reserva_coworking.application.validator.BookingValidator;
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
    private final BookingDelete delete;
    private final BookingUpdate update;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request,
            @AuthenticationPrincipal CustomUserPrincipal principal
            ){

        BookingResponse response = create.execute(request,principal);

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

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable Long  bookingId,
            @AuthenticationPrincipal CustomUserPrincipal principal){
        delete.execute(principal,bookingId);
         return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> updateBooking(
            @AuthenticationPrincipal CustomUserPrincipal principal,
            @PathVariable Long bookingId,
            @RequestBody BookingRequest request
    ){

        BookingResponse response = update.execute(principal,bookingId,request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
