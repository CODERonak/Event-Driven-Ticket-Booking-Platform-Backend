package com.product.TicketBookingSystem.events.internal.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEventRequest {

    @NotBlank(message = "Event title cannot be blank")
    private String title;

    @NotBlank(message = "Event description cannot be blank")
    private String description;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotBlank(message = "Event location cannot be blank")
    private String location;

    @NotNull(message = "Event time cannot be null")
    @Future(message = "Event time must be in the future")
    private Instant eventTime;

    @NotNull(message = "Total seats cannot be null")
    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;
}
