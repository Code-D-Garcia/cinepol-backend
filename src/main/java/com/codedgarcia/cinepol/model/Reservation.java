package com.codedgarcia.cinepol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "ticket_id", unique = true)
    private Ticket ticket;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;


    private OffsetDateTime reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
