package com.codedgarcia.cinepol.dto.reservation;

import java.util.UUID;

public record ReservationCreateRequest(
        UUID userId,
        UUID ticketId
) {
}
