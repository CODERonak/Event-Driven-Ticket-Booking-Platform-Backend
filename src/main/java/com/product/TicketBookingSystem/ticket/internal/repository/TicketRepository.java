package com.product.TicketBookingSystem.ticket.internal.repository;

import com.product.TicketBookingSystem.ticket.internal.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
