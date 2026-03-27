package com.example.sistema_de_reserva_coworking.domain.repository;

import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;

import java.time.LocalDate;
import java.util.List;


public interface BookingRepository {

    Booking save(Booking booking);

    boolean existsById( CompoundKey id );

    void deleteById(CompoundKey id);

    boolean existsBySpaceIdAndDateAndSlot (Long spaceId, LocalDate date, ReservationSlot slot);

    List<Booking> findAll ();

    List<Booking> findByUserId (Long id);


}
