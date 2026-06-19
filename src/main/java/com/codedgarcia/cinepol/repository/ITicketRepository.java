package com.codedgarcia.cinepol.repository;

import com.codedgarcia.cinepol.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ITicketRepository extends JpaRepository<Ticket, UUID> {
    Optional<Ticket> findBySeatNameIgnoreCase(String seatName);
    boolean existsBySeatNameIgnoreCase(String seatName);
}
