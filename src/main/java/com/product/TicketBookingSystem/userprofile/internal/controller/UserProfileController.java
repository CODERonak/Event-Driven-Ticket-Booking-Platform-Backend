package com.product.TicketBookingSystem.userprofile.internal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.TicketBookingSystem.userprofile.api.UserProfileService;
import com.product.TicketBookingSystem.userprofile.internal.dto.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
@Tag(name = "User Profile", description = "Operations related to user profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user profile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User profile created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request — validation failed"),
            @ApiResponse(responseCode = "409", description = "User profile already exists")
    })
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody @Valid UserProfileRequest request) {
        var userProfile = userProfileService.createProfile(request);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing user profile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User profile updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request — validation failed"),
            @ApiResponse(responseCode = "404", description = "User profile not found")
    })
    public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequest request) {
        var userProfile = userProfileService.updateUserProfile(request);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/my-profile")
    @Operation(summary = "Get the user profile of the currently logged-in user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User profile found"),
            @ApiResponse(responseCode = "404", description = "User profile not found")
    })
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        var userProfile = userProfileService.getUserProfile();
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
