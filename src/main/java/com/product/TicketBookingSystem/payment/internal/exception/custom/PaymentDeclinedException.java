package com.product.TicketBookingSystem.payment.internal.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Thrown when the user has insufficient funds or the card was declined
@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class PaymentDeclinedException extends RuntimeException {
    public PaymentDeclinedException(String message) {
        super(message);
    }
}
