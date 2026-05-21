package com.product.TicketBookingSystem.events.internal.dto;

import java.util.List;
import java.util.UUID;

public record SeatAvailabilityResponse(
    UUID eventId,
    List<String> availableSeats,
    Integer totalAvailable
) {}
