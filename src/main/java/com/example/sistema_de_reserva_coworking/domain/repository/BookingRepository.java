package com.example.sistema_de_reserva_coworking.domain.repository;

import com.example.sistema_de_reserva_coworking.application.dto.UserAndSpace;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface BookingRepository {

    Booking save(Booking booking);

    boolean existsByUserId(Long id);

    void deleteById(Long id);

    boolean existsBySpaceIdAndDateAndSlot (Long spaceId, LocalDate date, ReservationSlot slot);

    Optional<Booking> findById (Long id);

    List<Booking> findByUserId (Long id);


}
