package com.product.TicketBookingSystem.auth.internal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.product.TicketBookingSystem.auth.internal.dto.*;
import com.product.TicketBookingSystem.auth.internal.model.entity.AuthUser;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    // Maps a Register Request to an Auth User Entity.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    AuthUser toEntity(RegisterRequest registerRequest);

    // Maps RegisterRequest to Register Response
    @Mapping(target = "role", ignore = true)
    RegisterResponse toRegisterResponse(AuthUser authUser);

    // Maps AuthUser to Login Response
    @Mapping(target = "token", ignore = true)
    LoginResponse toLoginResponse(AuthUser authUser);

}
