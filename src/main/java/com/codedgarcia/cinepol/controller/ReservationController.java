package com.codedgarcia.cinepol.controller;

import com.codedgarcia.cinepol.dto.reservation.ReservationCreateRequest;
import com.codedgarcia.cinepol.dto.reservation.ReservationResponse;
import com.codedgarcia.cinepol.service.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationCreateRequest request) {
        ReservationResponse response = reservationService.createReservation(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm/{userId}")
    public ResponseEntity<ReservationResponse> confirmReservation(@PathVariable UUID userId) {
        ReservationResponse response = reservationService.confirmReservation(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable UUID id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ReservationResponse> getReservationByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(reservationService.getReservationByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable UUID id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
