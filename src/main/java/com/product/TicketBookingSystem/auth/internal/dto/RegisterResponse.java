package com.product.TicketBookingSystem.auth.internal.dto;

import com.product.TicketBookingSystem.auth.internal.model.enums.Role;

public record RegisterResponse(
                String email,
                Role role) {
}
