package com.product.TicketBookingSystem.userprofile.internal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {
    private String name;
    private String phone;
    private String address;
}
