package com.product.TicketBookingSystem.payment.internal.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

// Thrown when searching for a payment record that doesn't exist
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(UUID paymentId) {
        super("Payment with ID " + paymentId + " not found.");
    }
}
