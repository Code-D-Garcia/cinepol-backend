package com.codedgarcia.cinepol.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyHasReservationException extends RuntimeException {
    public UserAlreadyHasReservationException(String message) {
        super(message);
    }
}
