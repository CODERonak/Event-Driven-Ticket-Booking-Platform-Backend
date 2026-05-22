package com.product.TicketBookingSystem.booking.internal.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.TicketBookingSystem.booking.internal.model.entity.Booking;
import com.product.TicketBookingSystem.booking.internal.model.enums.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUserId(UUID userId);

    List<Booking> findByEventIdAndStatus(UUID eventId, BookingStatus status);
}
