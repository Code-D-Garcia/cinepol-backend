package com.codedgarcia.cinepol.dto.reservation;

import com.codedgarcia.cinepol.dto.ticket.TicketResponse;
import com.codedgarcia.cinepol.dto.user.UserResponse;
import com.codedgarcia.cinepol.model.ReservationStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ReservationResponse(
        UUID id,
        UserResponse user,
        TicketResponse ticket,
        OffsetDateTime reservationDate,
        ReservationStatus status
) {
}

