package com.product.TicketBookingSystem.auth.api;

import com.product.TicketBookingSystem.auth.internal.dto.*;

// Interface for Auth User Service
public interface AuthUserService {
    RegisterResponse registerUser(RegisterRequest registerRequest);

    LoginResponse loginUser(LoginRequest LoginRequest);
}