package com.product.TicketBookingSystem.payment.api;

import java.util.UUID;

import com.product.TicketBookingSystem.payment.internal.dto.PaymentRequest;
import com.product.TicketBookingSystem.payment.internal.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(UUID bookingId, PaymentRequest request);

    PaymentResponse getPayment(UUID paymentId);

    PaymentResponse getPaymentByBooking(UUID bookingId);

    String getPaymentStatus(UUID paymentId);

    void refundPayment(UUID paymentId);

    boolean hasPayment(UUID bookingId);

    PaymentResponse getPaymentByTransaction(String transactionId);

}
