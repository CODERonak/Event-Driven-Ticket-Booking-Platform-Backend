package com.product.TicketBookingSystem.userprofile.internal.dto;

public record UserProfileResponse(
        String name,
        String phone,
        String address,
        String email) {
}
