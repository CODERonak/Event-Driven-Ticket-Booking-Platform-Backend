package com.product.TicketBookingSystem.auth.api;

import java.util.UUID;

public interface SecurityService {
    UUID getCurrentUser();

    void validateUser(UUID userId);
}
