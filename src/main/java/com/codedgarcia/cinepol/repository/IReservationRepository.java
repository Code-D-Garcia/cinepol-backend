package com.codedgarcia.cinepol.repository;

import com.codedgarcia.cinepol.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IReservationRepository extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findByUserId(UUID userId);
    Optional<Reservation> findByTicketId(UUID ticketId);
    boolean existsByUserId(UUID userId);
    boolean existsByTicketId(UUID ticketId);
}
