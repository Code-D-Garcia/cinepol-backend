package com.codedgarcia.cinepol.service;

import com.codedgarcia.cinepol.dto.ticket.TicketCreateRequest;
import com.codedgarcia.cinepol.dto.ticket.TicketResponse;

import java.util.List;
import java.util.UUID;

public interface ITicketService {
    TicketResponse createTicket(TicketCreateRequest request);
    List<TicketResponse> getAllTickets();
    TicketResponse getTicketById(UUID id);
    void deleteTicket(UUID id);
}
