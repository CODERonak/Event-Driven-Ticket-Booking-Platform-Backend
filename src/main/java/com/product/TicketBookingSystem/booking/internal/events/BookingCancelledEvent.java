package com.product.TicketBookingSystem.booking.internal.events;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCancelledEvent {
    private UUID bookingId;
    private UUID userId;
    private long timestamp;
}