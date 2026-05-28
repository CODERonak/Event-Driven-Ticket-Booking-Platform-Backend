package com.product.TicketBookingSystem.booking.internal.service;

import com.product.TicketBookingSystem.EventPublisher;
import com.product.TicketBookingSystem.auth.api.SecurityService;
import com.product.TicketBookingSystem.booking.api.BookingService;
import com.product.TicketBookingSystem.booking.internal.dto.*;
import com.product.TicketBookingSystem.booking.internal.events.BookingCreatedEvent;
import com.product.TicketBookingSystem.booking.internal.exception.custom.*;
import com.product.TicketBookingSystem.booking.internal.mapper.BookingMapper;
import com.product.TicketBookingSystem.booking.internal.model.entity.Booking;
import com.product.TicketBookingSystem.booking.internal.model.entity.BookingSeat;
import com.product.TicketBookingSystem.booking.internal.model.enums.BookingStatus;
import com.product.TicketBookingSystem.booking.internal.repository.BookingRepository;
import com.product.TicketBookingSystem.events.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final EventService eventService;
    private final EventPublisher eventPublisher;
    private final BookingMapper bookingMapper;
    private final SecurityService securityService;

    @Override
    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {
        UUID userId = securityService.getCurrentUser();

        request.getSeatNumbers().forEach(seat -> eventService.bookSeat(request.getEventId(), seat));

        var event = eventService.getEvent(request.getEventId());
        java.math.BigDecimal totalAmount = event.price().multiply(
                java.math.BigDecimal.valueOf(request.getSeatNumbers().size()));

        // 3. Map to entity
        Booking booking = bookingMapper.toEntity(request);
        booking.setUserId(userId);
        booking.setStatus(BookingStatus.PENDING);
        booking.setAmount(totalAmount);

        if (booking.getSeats() != null) {
            for (BookingSeat seat : booking.getSeats()) {
                seat.setBooking(booking);
            }
        }
    
        Booking savedBooking = bookingRepository.save(booking);

        eventPublisher.publish(BookingCreatedEvent.builder()
                .bookingId(savedBooking.getId())
                .userId(userId)
                .eventId(request.getEventId())
                .seatNumbers(request.getSeatNumbers())
                .amount(totalAmount)
                .timestamp(System.currentTimeMillis())
                .build());

        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse getBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        // Ownership Validation
        securityService.validateUser(booking.getUserId());

        return bookingMapper.toResponse(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getUserBookings() {

        UUID userId = securityService.getCurrentUser();

        // Validation: Only allow users to fetch their own bookings
        securityService.validateUser(userId);

        return bookingRepository.findByUserId(userId).stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void confirmBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        securityService.validateUser(booking.getUserId());

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        securityService.validateUser(booking.getUserId());

        booking.getSeats().forEach(s -> eventService.releaseSeat(booking.getEventId(), s.getSeatNumber()));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}