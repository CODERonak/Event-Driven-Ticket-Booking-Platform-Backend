package com.product.TicketBookingSystem.booking.api;

import java.util.List;
import java.util.UUID;

import com.product.TicketBookingSystem.booking.internal.dto.BookingResponse;
import com.product.TicketBookingSystem.booking.internal.dto.BookingStatusResponse;
import com.product.TicketBookingSystem.booking.internal.dto.CreateBookingRequest;

public interface BookingService {
    BookingResponse createBooking(UUID userId, CreateBookingRequest request);

    BookingResponse getBooking(UUID bookingId);

    List<BookingResponse> getUserBookings(UUID userId);

    BookingStatusResponse getBookingStatus(UUID bookingId);

    void cancelBooking(UUID bookingId);

    void confirmBooking(UUID bookingId);
}
