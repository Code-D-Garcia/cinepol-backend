package com.codedgarcia.cinepol.service.impl;

import com.codedgarcia.cinepol.dto.ticket.TicketCreateRequest;
import com.codedgarcia.cinepol.dto.ticket.TicketResponse;
import com.codedgarcia.cinepol.mapper.TicketMapper;
import com.codedgarcia.cinepol.model.Ticket;
import com.codedgarcia.cinepol.repository.IReservationRepository;
import com.codedgarcia.cinepol.repository.ITicketRepository;
import com.codedgarcia.cinepol.service.ITicketService;
import com.codedgarcia.cinepol.shared.exception.DuplicateResourceException;
import com.codedgarcia.cinepol.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements ITicketService {

    private final ITicketRepository ticketRepository;
    private final IReservationRepository reservationRepository;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional
    public TicketResponse createTicket(TicketCreateRequest request) {
        if (ticketRepository.existsBySeatNameIgnoreCase(request.seatName())) {
            throw new DuplicateResourceException("El asiento ya existe: " + request.seatName());
        }
        Ticket ticket = ticketMapper.toEntity(request);
        Ticket saved = ticketRepository.save(ticket);
        return ticketMapper.toResponse(saved, false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        
        Set<UUID> reservedTicketIds = reservationRepository.findAll().stream()
                .map(r -> r.getTicket().getId())
                .collect(Collectors.toSet());

        return tickets.stream()
                .map(t -> ticketMapper.toResponse(t, reservedTicketIds.contains(t.getId())))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse getTicketById(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado con id: " + id));
        return ticketMapper.toResponse(ticket);
    }

    @Override
    @Transactional
    public void deleteTicket(UUID id) {
        if (!ticketRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asiento no encontrado con id: " + id);
        }
        ticketRepository.deleteById(id);
    }
}
