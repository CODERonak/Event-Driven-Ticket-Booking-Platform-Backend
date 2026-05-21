package com.product.TicketBookingSystem.userprofile.internal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {

    @NotBlank(message = "Please provide your name.")
    private String name;

    @NotBlank(message = "Please provide your phone number.")
    private String phone;

    @NotBlank(message = "Please provide your address.")
    private String address;
}
