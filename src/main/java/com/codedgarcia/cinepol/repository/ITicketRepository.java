package com.codedgarcia.cinepol.repository;

import com.codedgarcia.cinepol.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findBySeatNameIgnoreCase(String seatName);
    Optional<Ticket> findById(Long id);
    boolean existsBySeatName(String seatName);
}
