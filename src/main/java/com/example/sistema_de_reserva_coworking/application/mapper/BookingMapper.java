package com.example.sistema_de_reserva_coworking.application.mapper;

import com.example.sistema_de_reserva_coworking.application.dto.booking.BookingResponse;
import com.example.sistema_de_reserva_coworking.domain.model.Booking;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.BookingEntity;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.CompoundKey;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.UserEntity;

public class BookingMapper {

    public static BookingEntity mapBookingToBookingEntity(Booking booking){
        if(booking == null) return null;

        CompoundKey bookingId = new CompoundKey(
                booking.getUser().getId(),
                booking.getSpace().getId()
        );

         UserEntity bookingUser = UserMapper.mapUsertoUserEntity(booking.getUser());
        SpaceEntity bookingSpace = SpaceMapper.mapSpaceToSpaceEntity(booking.getSpace());


        return BookingEntity.builder()
                .id(bookingId)
                .user(bookingUser)
                .space(bookingSpace)
                .date(booking.getDate())
                .slot(booking.getSlot())
                .attendees(booking.getAttendees())
                .build();
    }

    public static Booking mapBookingEntityToBooking(BookingEntity bookingEntity){
        if(bookingEntity == null) return null;

        User user = UserMapper.mapUserEntityToUser(bookingEntity.getUser());
        Space  space = SpaceMapper.mapSpaceEntityToSpace(bookingEntity.getSpace());

        return Booking.builder()
                .user(user)
                .space(space)
                .date(bookingEntity.getDate())
                .slot(bookingEntity.getSlot())
                .attendees(bookingEntity.getAttendees())
                .build();
    }

    public static BookingResponse mapBookingToBookingResponse(Booking booking){
        if(booking == null) return null;

        return BookingResponse.builder()
                .nameUser(booking.getUser().getUsername())
                .nameSpace(booking.getSpace().getName())
                .date(booking.getDate())
                .slot(booking.getSlot())
                .attendees(booking.getAttendees())
                .build();
    }




}
