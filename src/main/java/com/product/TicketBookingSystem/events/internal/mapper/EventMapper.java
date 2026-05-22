
package com.product.TicketBookingSystem.events.internal.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.product.TicketBookingSystem.events.internal.dto.CreateEventRequest;
import com.product.TicketBookingSystem.events.internal.dto.EventResponse;
import com.product.TicketBookingSystem.events.internal.dto.SeatAvailabilityResponse;
import com.product.TicketBookingSystem.events.internal.model.entity.Event;
import com.product.TicketBookingSystem.events.internal.model.entity.Seat;

/**
 * Mapper for converting between event-related DTOs and entities.
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * Converts a CreateEventRequest DTO to an Event entity.
     *
     * request The CreateEventRequest DTO.
     * The Event entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "availableSeats", source = "totalSeats")
    Event toEntity(CreateEventRequest request);

    /**
     * Converts an Event entity to an EventResponse DTO.
     *
     * event The Event entity.
     * The EventResponse DTO.
     */
    EventResponse toResponse(Event event);

    /**
     * Converts a list of Event entities to a list of EventResponse DTOs.
     *
     * events The list of Event entities.
     * The list of EventResponse DTOs.
     */
    List<EventResponse> toResponseList(List<Event> events);

    /**
     * Converts an Event entity and a list of Seat entities to a
     * SeatAvailabilityResponse DTO.
     *
     * event The Event entity.
     * seats The list of Seat entities.
     * The SeatAvailabilityResponse DTO.
     */
    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "availableSeats", source = "seats", qualifiedByName = "toSeatNumbers")
    @Mapping(target = "totalAvailable", source = "seats", qualifiedByName = "countSeats")
    SeatAvailabilityResponse toSeatAvailabilityResponse(Event event, List<Seat> seats);

    /**
     * Converts a list of Seat entities to a list of seat numbers.
     *
     * seats The list of Seat entities.
     * The list of seat numbers.
     */
    @Named("toSeatNumbers")
    default List<String> toSeatNumbers(List<Seat> seats) {
        if (seats == null)
            return Collections.emptyList();
        return seats.stream().map(Seat::getSeatNumber).collect(Collectors.toList());
    }

    /**
     * Counts the number of seats in a list.
     *
     * seats The list of Seat entities.
     * The number of seats.
     */
    @Named("countSeats")
    default Integer countSeats(List<Seat> seats) {
        return (seats == null) ? 0 : seats.size();
    }
}
