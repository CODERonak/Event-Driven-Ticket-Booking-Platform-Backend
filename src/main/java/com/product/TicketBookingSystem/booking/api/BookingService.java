package com.product.TicketBookingSystem.booking.api;

import java.util.List;
import java.util.UUID;

import com.product.TicketBookingSystem.booking.internal.dto.BookingResponse;
import com.product.TicketBookingSystem.booking.internal.dto.CreateBookingRequest;

public interface BookingService {
   BookingResponse createBooking(CreateBookingRequest request);

   BookingResponse getBooking(UUID bookingId);

   List<BookingResponse> getUserBookings();

   void cancelBooking(UUID bookingId);

   void confirmBooking(UUID bookingId);
}