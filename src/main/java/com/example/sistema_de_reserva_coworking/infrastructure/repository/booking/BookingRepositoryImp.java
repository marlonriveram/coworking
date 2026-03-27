package com.example.sistema_de_reserva_coworking.infrastructure.repository.booking;

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
    public boolean existsById(CompoundKey id) {
        return jpaBookingRepository.existsById(id);
    }


    @Override
    public void deleteById(CompoundKey id) {
        jpaBookingRepository.deleteById(id);
    }

    @Override
    public boolean existsBySpaceIdAndDateAndSlot(Long spaceId, LocalDate date, ReservationSlot slot) {
        return jpaBookingRepository.existsBySpaceIdAndDateAndSlot(spaceId, date, slot);
    }

    @Override
    public List<Booking> findAll() {
        return jpaBookingRepository.findAll().stream().map(BookingMapper::mapBookingEntityToBooking).toList();
    }

    @Override
    public List<Booking> findByUserId(Long id) {
        return jpaBookingRepository.findByUserId(id).stream().map(BookingMapper::mapBookingEntityToBooking).toList();
    }

}
