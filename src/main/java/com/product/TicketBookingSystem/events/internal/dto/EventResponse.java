package com.product.TicketBookingSystem.events.internal.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record EventResponse(
    UUID id,
    String title,
    String description,
    BigDecimal price,
    String location,
    Instant eventTime,
    Integer totalSeats,
    Integer availableSeats
) {}
