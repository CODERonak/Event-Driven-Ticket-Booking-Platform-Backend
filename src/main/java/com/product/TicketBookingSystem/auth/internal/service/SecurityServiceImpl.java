package com.product.TicketBookingSystem.auth.internal.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.product.TicketBookingSystem.auth.api.interfaces.SecurityService;
import com.product.TicketBookingSystem.auth.internal.security.UserDetailsImpl;
import com.product.TicketBookingSystem.common.exceptions.custom.AccessDeniedException;
import com.product.TicketBookingSystem.common.exceptions.custom.UserNotFoundException;

/**
 * Implementation of SecurityService.
 * Handles authenticated user retrieval and ownership validation.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    /**
     * Retrieves the currently authenticated user from the SecurityContext.
     * Validates that the authentication is not null or anonymous.
     */
    @Override
    public UUID getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new UserNotFoundException("No authenticated user found");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Return just the ID (UUID)
        return userDetails.getUser().getId();
    }

    /**
     * Validates that the currently authenticated user matches the given user ID.
     * Throws AccessDeniedException if the user is not the owner of the resource.
     */
    @Override
    public void validateUser(UUID userId) {
        UUID currentUserId = getCurrentUser();

        // Since currentUserId is a UUID, you compare it directly
        if (!currentUserId.equals(userId)) {
            throw new AccessDeniedException("You do not have permission to modify this resource");
        }
    }
}