package com.product.TicketBookingSystem.booking.internal.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {
    @NotNull(message = "Event ID is required to process a booking")
    private UUID eventId;

    @NotEmpty(message = "At least one seat must be selected for the booking")
    private List<
        @NotNull(message = "Seat number cannot be null") 
        @NotEmpty(message = "Seat number cannot be blank") 
        String> seatNumbers;
}
