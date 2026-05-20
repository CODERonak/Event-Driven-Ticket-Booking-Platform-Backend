package com.product.TicketBookingSystem.auth.api.interfaces;

import java.util.UUID;

public interface SecurityService {
    UUID getCurrentUser();

    void validateUser(UUID userId);
}
