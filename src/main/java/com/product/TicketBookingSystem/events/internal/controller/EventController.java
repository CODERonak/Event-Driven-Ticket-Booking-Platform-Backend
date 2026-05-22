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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<EventResponse> createEvent(@RequestBody CreateEventRequest request) {
        EventResponse response = eventService.createEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID eventId) {
        EventResponse response = eventService.getEvent(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> response = eventService.getAllEvents();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{eventId}/available-seats")
    public ResponseEntity<SeatAvailabilityResponse> getAvailableSeats(@PathVariable UUID eventId) {
        SeatAvailabilityResponse response = eventService.getAvailableSeats(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
