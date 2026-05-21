package com.product.TicketBookingSystem.userprofile.api;

import com.product.TicketBookingSystem.userprofile.internal.dto.*;

import java.util.UUID;

public interface UserProfileService {
    UserProfileResponse updateUserProfile(UpdateUserProfileRequest updateRequest);

    UserProfileResponse getUserProfile();

    void createProfile(UUID authUserId);
}
