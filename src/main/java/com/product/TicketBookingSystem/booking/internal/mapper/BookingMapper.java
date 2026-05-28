package com.product.TicketBookingSystem.booking.internal.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.product.TicketBookingSystem.booking.internal.dto.BookingResponse;
import com.product.TicketBookingSystem.booking.internal.dto.CreateBookingRequest;
import com.product.TicketBookingSystem.booking.internal.model.entity.Booking;
import com.product.TicketBookingSystem.booking.internal.model.entity.BookingSeat;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    /**
     * Maps the incoming Request DTO to the Booking entity.
     * Note: We ignore fields that are managed by the database/service (id, status,
     * seats, etc).
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    // Explicitly ignore fields not in your DTO
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "seats", source = "seatNumbers", qualifiedByName = "toBookingSeats")
    @Mapping(target = "seatCount", expression = "java(request.getSeatNumbers().size())")
    Booking toEntity(CreateBookingRequest request);

    /**
     * Maps the Booking entity to the Response DTO.
     */

    @Mapping(target = "status", expression = "java(booking.getStatus().name())")
    @Mapping(target = "seatNumbers", expression = "java(booking.getSeats().stream().map(s -> s.getSeatNumber()).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "createdAt", expression = "java(booking.getCreatedAt().toString())")
    BookingResponse toResponse(Booking booking);

    /**
     * Helper to convert list of seat strings into BookingSeat entities.
     */
    @Named("toBookingSeats")
    default List<BookingSeat> toBookingSeats(List<String> seatNumbers) {
        if (seatNumbers == null)
            return null;

        return seatNumbers.stream()
                .map(seatNum -> {
                    BookingSeat bs = new BookingSeat();
                    bs.setSeatNumber(seatNum);
                    return bs;
                })
                .collect(Collectors.toList());
    }
}
