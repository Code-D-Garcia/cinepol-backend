package com.codedgarcia.cinepol.mapper;

import com.codedgarcia.cinepol.dto.reservation.ReservationResponse;
import com.codedgarcia.cinepol.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;

    public ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                userMapper.toResponse(reservation.getUser()),
                ticketMapper.toResponse(reservation.getTicket()),
                reservation.getReservationDate(),
                reservation.getStatus()
        );
    }
}

