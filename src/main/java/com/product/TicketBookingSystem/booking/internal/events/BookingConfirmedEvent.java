package com.product.TicketBookingSystem.booking.internal.events;

import lombok.*;
import java.util.UUID;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingConfirmedEvent {
    private UUID bookingId;
    private UUID userId;
    private UUID eventId;
    private Instant confirmedAt; // Use Instant for better date-time handling
}
