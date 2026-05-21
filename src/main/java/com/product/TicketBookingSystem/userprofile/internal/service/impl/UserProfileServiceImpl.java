package com.product.TicketBookingSystem.userprofile.internal.service.impl;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.product.TicketBookingSystem.auth.api.interfaces.SecurityService;
import com.product.TicketBookingSystem.common.exceptions.custom.UserNotFoundException;
import com.product.TicketBookingSystem.userprofile.api.UserProfileService;

import lombok.RequiredArgsConstructor;
import com.product.TicketBookingSystem.userprofile.internal.dto.UpdateUserProfileRequest;
import com.product.TicketBookingSystem.userprofile.internal.dto.UserProfileRequest;
import com.product.TicketBookingSystem.userprofile.internal.dto.UserProfileResponse;
import com.product.TicketBookingSystem.userprofile.internal.mapper.UserProfileMapper;
import com.product.TicketBookingSystem.userprofile.internal.repository.UserProfileRepository;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final SecurityService securityService;

    @Override
    public UserProfileResponse createProfile(UserProfileRequest userProfileRequest) {

        UUID authUserId = securityService.getCurrentUser();

        if (userProfileRepository.existsByAuthUserId(authUserId)) {
            throw new UserNotFoundException("Profile already exists for this user");
        }

        var userProfile = userProfileMapper.toEntity(userProfileRequest);
        userProfile.setAuthUserId(authUserId);
        userProfileRepository.save(userProfile);

        String verifiedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return new UserProfileResponse(
                userProfile.getName(),
                userProfile.getPhone(),
                userProfile.getAddress(),
                verifiedEmail);
    }

    @Override
    public UserProfileResponse updateUserProfile(UpdateUserProfileRequest updateRequest) {
        UUID authUserId = securityService.getCurrentUser();

        var userProfile = userProfileRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new UserNotFoundException("Profile not found for this user"));

        userProfileMapper.toUpdateRequest(updateRequest, userProfile);
        userProfileRepository.save(userProfile);

        String verifiedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return new UserProfileResponse(
                userProfile.getName(),
                userProfile.getPhone(),
                userProfile.getAddress(),
                verifiedEmail);
    }

    @Override
    public UserProfileResponse getUserProfile() {
        UUID authUserId = securityService.getCurrentUser();

        var userProfile = userProfileRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new UserNotFoundException("Profile not found for this user"));

        String verifiedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return new UserProfileResponse(
                userProfile.getName(),
                userProfile.getPhone(),
                userProfile.getAddress(),
                verifiedEmail);
    }

}
