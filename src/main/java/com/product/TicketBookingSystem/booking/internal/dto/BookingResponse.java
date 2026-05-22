package com.product.TicketBookingSystem.booking.internal.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record BookingResponse(UUID id, UUID eventId, String status, BigDecimal amount, List<String> seatNumbers, String createdAt) {
}
