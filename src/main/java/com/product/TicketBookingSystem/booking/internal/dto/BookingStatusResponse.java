package com.product.TicketBookingSystem.booking.internal.dto;

import java.util.UUID;

public record BookingStatusResponse(
    UUID id, 
    String status, 
    String updatedAt) {
}
