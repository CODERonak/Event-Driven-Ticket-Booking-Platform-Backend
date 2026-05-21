package com.product.TicketBookingSystem.userprofile.api;

import java.util.UUID;

import com.product.TicketBookingSystem.userprofile.internal.dto.*;

public interface UserProfileService {
    UserProfileResponse createProfile(UserProfileRequest userProfileRequest);

    UserProfileResponse updateUserProfile(UpdateUserProfileRequest updateRequest, UUID authUserId);

    UserProfileResponse getUserProfile();

}
