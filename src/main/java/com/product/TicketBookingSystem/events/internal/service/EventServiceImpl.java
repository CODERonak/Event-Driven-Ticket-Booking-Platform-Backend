package com.product.TicketBookingSystem.events.internal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.TicketBookingSystem.events.api.EventService;
import com.product.TicketBookingSystem.events.internal.dto.CreateEventRequest;
import com.product.TicketBookingSystem.events.internal.dto.EventResponse;
import com.product.TicketBookingSystem.events.internal.dto.SeatAvailabilityResponse;
import com.product.TicketBookingSystem.events.internal.exception.custom.EventNotFoundException;
import com.product.TicketBookingSystem.events.internal.exception.custom.SeatNotAvailableException;
import com.product.TicketBookingSystem.events.internal.model.entity.Event;
import com.product.TicketBookingSystem.events.internal.model.enums.SeatStatus;
import com.product.TicketBookingSystem.events.internal.model.entity.Seat;
import com.product.TicketBookingSystem.events.internal.repository.EventRepository;
import com.product.TicketBookingSystem.events.internal.repository.SeatRepository;
import com.product.TicketBookingSystem.events.internal.mapper.EventMapper;

import lombok.RequiredArgsConstructor;

/**
 * Service implementation for managing event lifecycle and ticket booking logic.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final EventMapper eventMapper;

    /**
     * Creates a new event and automatically generates the seat grid.
     */
    @Override
    public EventResponse createEvent(CreateEventRequest request) {
        // Convert DTO to Entity
        Event event = eventMapper.toEntity(request);

        // Persist the event to obtain the ID
        Event savedEvent = eventRepository.save(event);

        // Initialize seat map for the event
        generateSeats(savedEvent);

        return eventMapper.toResponse(savedEvent);
    }

    /**
     * Retrieves a single event by its unique ID.
     */
    @Override
    public EventResponse getEvent(UUID eventId) {
        
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new EventNotFoundException("Event not found: " + eventId));
        
        return eventMapper.toResponse(event);
    }

    /**
     * Lists all events available in the system.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {
        return eventMapper.toResponseList(eventRepository.findAll());
    }

    /**
     * Returns a summary of available seats for a specific event.
     */
    @Override
    @Transactional(readOnly = true)
    public SeatAvailabilityResponse getAvailableSeats(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        List<Seat> availableSeats = seatRepository.findByEventIdAndStatus(eventId, SeatStatus.AVAILABLE);

        return eventMapper.toSeatAvailabilityResponse(event, availableSeats);
    }

    /**
     * Books a specific seat and updates the event's available seat count.
     */
    @Override
    public void bookSeat(UUID eventId, String seatNumber) {
        Seat seat = seatRepository.findByEventIdAndSeatNumber(eventId, seatNumber)
                .orElseThrow(() -> new SeatNotAvailableException("Seat not found"));

        // Validate seat status
        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            throw new IllegalStateException("Seat is already booked");
        }

        // Update seat status
        seat.setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);

        // Update the event's available capacity
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);
    }

    /**
     * Releases a seat back to available status.
     */
    @Override
    public void releaseSeat(UUID eventId, String seatNumber) {
        Seat seat = seatRepository.findByEventIdAndSeatNumber(eventId, seatNumber)
                .orElseThrow(() -> new SeatNotAvailableException("Seat not found"));

        seat.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);

        // Note: You should ideally increment event.setAvailableSeats here as well
    }

    /**
     * Helper method to generate a grid of seats (e.g., A1, A2...) using bulk
     * insert.
     */
    private void generateSeats(Event event) {
        int seatsPerRow = 10;
        int total = event.getTotalSeats();
        List<Seat> seats = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            // Determine row letter and column number
            int row = i / seatsPerRow;
            int col = (i % seatsPerRow) + 1;
            String seatNumber = (char) ('A' + row) + String.valueOf(col);

            // Build seat entity
            Seat seat = new Seat();
            seat.setEventId(event.getId());
            seat.setSeatNumber(seatNumber);
            seat.setStatus(SeatStatus.AVAILABLE);

            seats.add(seat);
        }
        // Use bulk save for performance
        seatRepository.saveAll(seats);
    }
}
