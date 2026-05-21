package com.product.TicketBookingSystem.events.internal.dto;

import java.time.Instant;
import java.util.UUID;

public record EventResponse(
    UUID id,
    String title,
    String description,
    String location,
    Instant eventTime,
    Integer totalSeats,
    Integer availableSeats
) {}
