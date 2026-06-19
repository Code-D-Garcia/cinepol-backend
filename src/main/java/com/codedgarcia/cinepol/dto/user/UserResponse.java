package com.codedgarcia.cinepol.dto.user;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username
) {
}
