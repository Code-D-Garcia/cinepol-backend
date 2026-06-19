package com.codedgarcia.cinepol.dto.ticket;

import java.util.UUID;

public record TicketResponse(
        UUID id,
        String seatName,
        boolean reserved
) {
}
