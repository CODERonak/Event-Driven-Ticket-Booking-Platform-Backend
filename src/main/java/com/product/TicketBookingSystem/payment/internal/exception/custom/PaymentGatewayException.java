package com.product.TicketBookingSystem.payment.internal.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Thrown when the payment gateway fails (e.g., Stripe/Razorpay connection error)
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class PaymentGatewayException extends RuntimeException {
    public PaymentGatewayException(String message) {
        super(message);
    }
}
