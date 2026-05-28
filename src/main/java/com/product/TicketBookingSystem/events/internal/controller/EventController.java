package com.product.TicketBookingSystem.events.internal.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.product.TicketBookingSystem.events.api.EventService;
import com.product.TicketBookingSystem.events.internal.dto.CreateEventRequest;
import com.product.TicketBookingSystem.events.internal.dto.EventResponse;
import com.product.TicketBookingSystem.events.internal.dto.SeatAvailabilityResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Operations related to events")
public class EventController {
    private final EventService eventService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Create a new event (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request — validation failed"),
            @ApiResponse(responseCode = "403", description = "Forbidden - user is not an admin")
    })
    public ResponseEntity<EventResponse> createEvent(@RequestBody CreateEventRequest request) {
        EventResponse response = eventService.createEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "Get an event by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID eventId) {
        EventResponse response = eventService.getEvent(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get a list of all events")
    @ApiResponse(responseCode = "200", description = "Events retrieved successfully")
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> response = eventService.getAllEvents();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{eventId}/available-seats")
    @Operation(summary = "Get the available seats for a specific event")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seat availability retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<SeatAvailabilityResponse> getAvailableSeats(@PathVariable UUID eventId) {
        SeatAvailabilityResponse response = eventService.getAvailableSeats(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
