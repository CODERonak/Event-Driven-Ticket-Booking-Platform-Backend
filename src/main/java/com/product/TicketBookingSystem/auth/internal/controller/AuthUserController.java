package com.product.TicketBookingSystem.auth.internal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.TicketBookingSystem.auth.api.interfaces.AuthUserService;
import com.product.TicketBookingSystem.auth.internal.dto.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication", description = "Endpoints for user registration and login. " +
                "Returns a JWT token on successful login.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {

        private final AuthUserService authUserService;

        @Operation(summary = "Register a new user", description = "Registers a new user account with email and password )")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "User registered successfully"),
                        @ApiResponse(responseCode = "409", description = "User already exists with this email"),
                        @ApiResponse(responseCode = "400", description = "Invalid request — validation failed")
        })
        @PostMapping("/register")
        public ResponseEntity<RegisterResponse> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
                var response = authUserService.registerUser(registerRequest);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        @Operation(summary = "Login with existing credentials", description = "Authenticates a user with email and password. "
                        +
                        "Returns a JWT token valid for 24 hours. " +
                        "Use this token in the Authorization header as: Bearer {token}.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Login successful"),
                        @ApiResponse(responseCode = "401", description = "Invalid email or password"),
                        @ApiResponse(responseCode = "400", description = "Invalid request — validation failed")
        })
        @PostMapping("/login")
        public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
                var response = authUserService.loginUser(loginRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
        }
}