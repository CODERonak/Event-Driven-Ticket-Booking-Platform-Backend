package com.product.TicketBookingSystem.auth.internal.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.TicketBookingSystem.auth.internal.exception.custom.InvalidCredentialsException;
import com.product.TicketBookingSystem.auth.internal.exception.custom.UserAlreadyExistsException;
import com.product.TicketBookingSystem.common.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Auth user exception handler for the application handling auth related
 * exceptions.
 * and returns a structured ErrorResponse with appropriate HTTP status codes.
 */
@RestControllerAdvice
public class AuthUserExceptionHandler {

    /**
     * Handles cases where the user already exists.
     * Returns 409 CONFLICT
     */

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException ex,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Handles cases where the user enters invalid credentials.
     * Returns 401 UNAUTHORIZED
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException ex,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }
}
