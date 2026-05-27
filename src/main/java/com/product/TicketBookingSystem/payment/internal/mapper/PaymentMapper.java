package com.product.TicketBookingSystem.payment.internal.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.product.TicketBookingSystem.payment.internal.dto.PaymentRequest;
import com.product.TicketBookingSystem.payment.internal.dto.PaymentResponse;
import com.product.TicketBookingSystem.payment.internal.model.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "status", target = "paymentStatus")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    PaymentResponse toPaymentResponse(Payment payment);

    List<PaymentResponse> toPaymentResponseList(List<Payment> payments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.product.TicketBookingSystem.payment.internal.model.enums.PaymentStatus.PENDING)")
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Payment toPaymentEntity(PaymentRequest request);
    
}
