package com.codedgarcia.cinepol.controller;

import com.codedgarcia.cinepol.dto.ticket.TicketCreateRequest;
import com.codedgarcia.cinepol.dto.ticket.TicketResponse;
import com.codedgarcia.cinepol.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketCreateRequest request) {
        TicketResponse response = ticketService.createTicket(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
