package com.product.TicketBookingSystem.auth.internal.dto;

public record LoginResponse(
                String email,
                String fullname,
                String token) {
}
