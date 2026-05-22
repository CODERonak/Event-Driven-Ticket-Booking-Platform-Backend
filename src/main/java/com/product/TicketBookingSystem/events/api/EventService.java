package com.product.TicketBookingSystem.events.api;

import java.util.List;
import java.util.UUID;

import com.product.TicketBookingSystem.events.internal.dto.CreateEventRequest;
import com.product.TicketBookingSystem.events.internal.dto.EventResponse;
import com.product.TicketBookingSystem.events.internal.dto.SeatAvailabilityResponse;

public interface EventService {
    EventResponse createEvent(CreateEventRequest request);

    EventResponse getEvent(UUID eventId);

    List<EventResponse> getAllEvents();

    SeatAvailabilityResponse getAvailableSeats(UUID eventId);

    void bookSeat(UUID eventId, String seatNumber);

    void releaseSeat(UUID eventId, String seatNumber);
}
