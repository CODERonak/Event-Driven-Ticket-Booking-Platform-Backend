package com.product.TicketBookingSystem.notification.internal.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.TicketBookingSystem.notification.internal.model.entity.Payment;
import com.product.TicketBookingSystem.notification.internal.model.enums.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByBookingId(UUID bookingId);

    List<Payment> findByStatus(PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.status = 'PENDING' AND p.createdAt < :expiryTime")
    List<Payment> findExpiredPendingPayments(@Param("expiryTime") Instant expiryTime);

    boolean existsByBookingId(UUID bookingId);

    List<Payment> findByPaymentMethod(String paymentMethod);
}
