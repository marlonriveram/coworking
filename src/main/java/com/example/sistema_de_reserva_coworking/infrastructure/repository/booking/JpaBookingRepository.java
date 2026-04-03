package com.example.sistema_de_reserva_coworking.infrastructure.repository.booking;

import com.example.sistema_de_reserva_coworking.application.dto.UserAndSpace;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.BookingEntity;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;
import com.example.sistema_de_reserva_coworking.infrastructure.repository.projections.SpaceCapacity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaBookingRepository extends JpaRepository<BookingEntity,Long> {
    void deleteById(CompoundKey id);

    boolean existsByUserId(Long id);

    boolean existsBySpaceIdAndDateAndSlot (Long spaceId, LocalDate date, ReservationSlot slot);

    List<BookingEntity> findByUserId(Long id);
}
