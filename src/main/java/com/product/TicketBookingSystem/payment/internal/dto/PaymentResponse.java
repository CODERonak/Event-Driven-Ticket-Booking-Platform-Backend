package com.product.TicketBookingSystem.payment.internal.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(
    UUID id,
    UUID bookingId,
    BigDecimal amount,
    String paymentStatus,
    String transactionId,
    String paymentMethod,
    Instant updatedAt
) {}
