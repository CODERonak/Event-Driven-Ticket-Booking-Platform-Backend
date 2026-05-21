package com.product.TicketBookingSystem.common.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.TicketBookingSystem.common.api.*;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Global exception handler for the application.
 * and returns a structured ErrorResponse with appropriate HTTP status codes.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handles cases where the requested user is not found
         * Returns 404 NOT_FOUND
         */

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserNotFoundException(
                        UserNotFoundException ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.NOT_FOUND;
                ErrorResponse errorResponse = new ErrorResponse(
                                Instant.now(),
                                status.value(),
                                status.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, status);
        }

        /**
         * Handles when the user does not have access to the requested resource
         * Returns 403 FORBIDDEN
         */

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponse> handleAccessDeniedException(
                        AccessDeniedException ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.FORBIDDEN;
                ErrorResponse errorResponse = new ErrorResponse(
                                Instant.now(),
                                status.value(),
                                status.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, status);
        }

        /**
         * Handles all unexpected exceptions not caught by other handlers.
         * Returns 500 Internal Server Error.
         */

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(
                        Exception ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
                ErrorResponse errorResponse = new ErrorResponse(
                                Instant.now(),
                                status.value(),
                                status.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, status);
        }
}