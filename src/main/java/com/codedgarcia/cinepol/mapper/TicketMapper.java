package com.codedgarcia.cinepol.mapper;

import com.codedgarcia.cinepol.dto.ticket.TicketCreateRequest;
import com.codedgarcia.cinepol.dto.ticket.TicketResponse;
import com.codedgarcia.cinepol.model.Ticket;
import com.codedgarcia.cinepol.repository.IReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final IReservationRepository reservationRepository;

    public TicketResponse toResponse(Ticket ticket) {
        boolean reserved = reservationRepository.existsByTicketId(ticket.getId());
        return new TicketResponse(ticket.getId(), ticket.getSeatName(), reserved);
    }

    public TicketResponse toResponse(Ticket ticket, boolean reserved) {
        return new TicketResponse(ticket.getId(), ticket.getSeatName(), reserved);
    }

    public Ticket toEntity(TicketCreateRequest request) {
        return Ticket.builder()
                .seatName(request.seatName())
                .build();
    }
}
