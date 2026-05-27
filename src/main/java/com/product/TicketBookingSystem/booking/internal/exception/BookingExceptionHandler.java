package com.product.TicketBookingSystem.booking.internal.exception;

import com.product.TicketBookingSystem.booking.internal.exception.custom.BookingExpiredException;
import com.product.TicketBookingSystem.booking.internal.exception.custom.BookingNotFoundException;
import com.product.TicketBookingSystem.booking.internal.exception.custom.InvalidBookingRequestException;
import com.product.TicketBookingSystem.common.api.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class BookingExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookingNotFoundException(BookingNotFoundException ex,
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

    @ExceptionHandler(InvalidBookingRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBookingRequestException(InvalidBookingRequestException ex,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(BookingExpiredException.class)
    public ResponseEntity<ErrorResponse> handleBookingExpiredException(BookingExpiredException ex,
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

}
