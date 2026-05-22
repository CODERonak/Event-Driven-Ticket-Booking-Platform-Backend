package com.product.TicketBookingSystem.booking.internal.events;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCreatedEvent {
    private UUID bookingId;
    private UUID userId;
    private UUID eventId;
    private String eventTitle; 
    private List<String> seatNumbers; 
    private BigDecimal amount;
    private long timestamp;
}