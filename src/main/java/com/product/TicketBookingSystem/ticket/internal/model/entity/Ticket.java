package com.product.TicketBookingSystem.ticket.internal.model.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets", indexes = {
        @Index(name = "idx_booking_id", columnList = "booking_id", unique = true)
})
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID bookingId;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false, length = 255)
    private String fileName;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

}
