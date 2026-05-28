package com.product.TicketBookingSystem.booking.internal.controller;

import com.product.TicketBookingSystem.booking.api.BookingService;
import com.product.TicketBookingSystem.booking.internal.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings", description = "Operations related to ticket bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Create a new booking")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid booking request"),
            @ApiResponse(responseCode = "404", description = "Event not found or seats not available")
    })
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Get a booking by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking found"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<BookingResponse> getBooking(@PathVariable UUID bookingId) {
        BookingResponse response = bookingService.getBooking(bookingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all bookings for the current user")
    public ResponseEntity<List<BookingResponse>> getUserBookings() {
        List<BookingResponse> response = bookingService.getUserBookings();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{bookingId}/confirm")
    @Operation(summary = "Confirm a booking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "409", description = "Booking has expired or is already confirmed")
    })
    public ResponseEntity<Void> confirmBooking(@PathVariable UUID bookingId) {
        bookingService.confirmBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{bookingId}/cancel")
    @Operation(summary = "Cancel a booking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Void> cancelBooking(@PathVariable UUID bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }
}
