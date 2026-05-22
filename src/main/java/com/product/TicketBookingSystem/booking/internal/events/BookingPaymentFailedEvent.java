package com.product.TicketBookingSystem.booking.internal.events;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingPaymentFailedEvent {
    private UUID bookingId;
    private UUID eventId;
    private List<String> seatNumbers;
    private String reason; 
}
