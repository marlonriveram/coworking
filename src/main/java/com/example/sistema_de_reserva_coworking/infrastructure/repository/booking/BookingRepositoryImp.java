package com.example.sistema_de_reserva_coworking.infrastructure.repository.booking;

import com.example.sistema_de_reserva_coworking.application.dto.UserAndSpace;
import com.example.sistema_de_reserva_coworking.application.mapper.BookingMapper;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import com.example.sistema_de_reserva_coworking.domain.repository.BookingRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.BookingEntity;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryImp implements BookingRepository {

    private final JpaBookingRepository jpaBookingRepository;

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = BookingMapper.mapBookingToBookingEntity(booking);
        BookingEntity response = jpaBookingRepository.save(entity);
        return BookingMapper.mapBookingEntityToBooking(response);
    }

    @Override
    public boolean existsByUserId(Long id) {
        return jpaBookingRepository.existsByUserId(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaBookingRepository.deleteById(id);
    }

    @Override
    public boolean existsBySpaceIdAndDateAndSlot(Long spaceId, LocalDate date, ReservationSlot slot) {
        return jpaBookingRepository.existsBySpaceIdAndDateAndSlot(spaceId, date, slot);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return jpaBookingRepository.findById(id).map(BookingMapper::mapBookingEntityToBooking);
    }

    @Override
    public List<Booking> findByUserId(Long id) {
        return jpaBookingRepository.findByUserId(id).stream().map(BookingMapper::mapBookingEntityToBooking).toList();
    }

}
