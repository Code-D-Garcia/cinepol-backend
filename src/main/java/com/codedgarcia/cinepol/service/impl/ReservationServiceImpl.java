package com.codedgarcia.cinepol.service.impl;

import com.codedgarcia.cinepol.dto.reservation.ReservationCreateRequest;
import com.codedgarcia.cinepol.dto.reservation.ReservationResponse;
import com.codedgarcia.cinepol.mapper.ReservationMapper;
import com.codedgarcia.cinepol.model.Reservation;
import com.codedgarcia.cinepol.model.ReservationStatus;
import com.codedgarcia.cinepol.model.Ticket;
import com.codedgarcia.cinepol.model.User;
import com.codedgarcia.cinepol.repository.IReservationRepository;
import com.codedgarcia.cinepol.repository.IUserRepository;
import com.codedgarcia.cinepol.repository.ITicketRepository;
import com.codedgarcia.cinepol.service.IReservationService;

import com.codedgarcia.cinepol.shared.exception.ResourceNotFoundException;
import com.codedgarcia.cinepol.shared.exception.SeatAlreadyReservedException;
import com.codedgarcia.cinepol.shared.exception.UserAlreadyHasReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IUserRepository userRepository;
    private final ITicketRepository ticketRepository;
    private final ReservationMapper reservationMapper;

    @Override
    @Transactional
    public ReservationResponse createReservation(ReservationCreateRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + request.userId()));

        if (reservationRepository.existsByUserId(user.getId())) {
            throw new UserAlreadyHasReservationException("El usuario '" + user.getUsername() + "' ya tiene una reserva.");
        }

        Ticket ticket = ticketRepository.findById(request.ticketId())
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado con id: " + request.ticketId()));

        if (reservationRepository.existsByTicketId(ticket.getId())) {
            throw new SeatAlreadyReservedException("El asiento '" + ticket.getSeatName() + "' ya está reservado.");
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .ticket(ticket)
                .reservationDate(OffsetDateTime.now())
                .status(ReservationStatus.HELD)
                .build();

        Reservation saved = reservationRepository.save(reservation);

        return reservationMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ReservationResponse confirmReservation(UUID userId) {
        Reservation reservation = reservationRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró reserva para el usuario con id: " + userId));

        if (reservation.getStatus() == ReservationStatus.CONFIRMED) {
            throw new UserAlreadyHasReservationException("Tu reserva ya está confirmada.");
        }

        reservation.setStatus(ReservationStatus.CONFIRMED);
        Reservation saved = reservationRepository.save(reservation);

        return reservationMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponse getReservationById(UUID id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponse getReservationByUserId(UUID userId) {
        return reservationRepository.findByUserId(userId)
                .map(reservationMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró reserva para el usuario con id: " + userId));
    }

    @Override
    @Transactional
    public void cancelReservation(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
        
        reservationRepository.delete(reservation);
    }
}
