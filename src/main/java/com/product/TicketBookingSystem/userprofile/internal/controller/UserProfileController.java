package com.product.TicketBookingSystem.userprofile.internal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.TicketBookingSystem.userprofile.api.UserProfileService;
import com.product.TicketBookingSystem.userprofile.internal.dto.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping("/create")
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody @Valid UserProfileRequest request) {
        var userProfile = userProfileService.createProfile(request);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequest request) {
        var userProfile = userProfileService.updateUserProfile(request);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        var userProfile = userProfileService.getUserProfile();
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
