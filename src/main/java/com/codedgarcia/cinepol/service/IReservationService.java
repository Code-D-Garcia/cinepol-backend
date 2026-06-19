package com.codedgarcia.cinepol.service;

import com.codedgarcia.cinepol.dto.reservation.ReservationCreateRequest;
import com.codedgarcia.cinepol.dto.reservation.ReservationResponse;

import java.util.List;
import java.util.UUID;

public interface IReservationService {
    ReservationResponse createReservation(ReservationCreateRequest request);
    ReservationResponse confirmReservation(UUID userId);
    List<ReservationResponse> getAllReservations();
    ReservationResponse getReservationById(UUID id);
    ReservationResponse getReservationByUserId(UUID userId);
    void cancelReservation(UUID id);
}
