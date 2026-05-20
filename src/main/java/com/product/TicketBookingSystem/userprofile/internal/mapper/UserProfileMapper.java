package com.product.TicketBookingSystem.userprofile.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.product.TicketBookingSystem.userprofile.internal.dto.*;
import com.product.TicketBookingSystem.userprofile.internal.model.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "authUserId", ignore = true)
    UserProfile toEntity(UserProfileRequest userProfileRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "authUserId", ignore = true)
    void toUpdateRequest(UpdateUserProfileRequest updateUserProfileRequest, @MappingTarget UserProfile userProfile);

    @Mapping(target = "email", ignore = true)
    UserProfileResponse toResponse(UserProfile userProfile);
}
