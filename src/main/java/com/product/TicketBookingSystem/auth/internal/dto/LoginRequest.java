package com.product.TicketBookingSystem.auth.internal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
                @NotBlank(message = "Please enter your email address.") @Email(message = "Please enter a valid email address.") String email,

                @NotBlank(message = "Please enter your password.") String password) {
}
