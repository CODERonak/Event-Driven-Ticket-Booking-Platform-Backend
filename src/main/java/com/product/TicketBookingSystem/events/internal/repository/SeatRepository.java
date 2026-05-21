package com.product.TicketBookingSystem.events.internal.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.TicketBookingSystem.events.internal.model.entity.Seat;
import com.product.TicketBookingSystem.events.internal.model.enums.SeatStatus;

import jakarta.persistence.LockModeType;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findByEventIdAndStatus(UUID eventId, SeatStatus status);
    
    @Query("SELECT s FROM Seat s WHERE s.eventId = :eventId AND s.status = 'AVAILABLE'")
    List<Seat> findAvailableSeatsByEventId(UUID eventId);
    
    Optional<Seat> findByEventIdAndSeatNumber(UUID eventId, String seatNumber);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Seat> findById(UUID seatId);
    
    int countByEventIdAndStatus(UUID eventId, SeatStatus status);
}
