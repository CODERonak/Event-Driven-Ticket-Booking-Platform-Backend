package com.product.TicketBookingSystem.auth.internal.service;

import com.product.TicketBookingSystem.auth.api.interfaces.AuthUserService;
import com.product.TicketBookingSystem.auth.internal.dto.*;
import com.product.TicketBookingSystem.auth.internal.exception.custom.InvalidCredentialsException;
import com.product.TicketBookingSystem.auth.internal.exception.custom.UserAlreadyExistsException;
import com.product.TicketBookingSystem.auth.internal.mapper.AuthUserMapper;
import com.product.TicketBookingSystem.auth.internal.model.enums.Role;
import com.product.TicketBookingSystem.auth.internal.repository.AuthUserRepository;
import com.product.TicketBookingSystem.auth.internal.security.jwt.JWTUtil;
import com.product.TicketBookingSystem.common.exceptions.custom.UserNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        if (authUserRepository.existsByEmail(registerRequest.getEmail()))
            throw new UserAlreadyExistsException("user already exists with this email");

        var authUser = authUserMapper.toEntity(registerRequest);

        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRole(Role.USER);

        authUserRepository.save(authUser);

        return authUserMapper.toRegisterResponse(authUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtil.generateToken(loginRequest.email());

            var user = authUserRepository.findByEmail(loginRequest.email())
                    .orElseThrow(() -> new UserNotFoundException("user not found"));

            return new LoginResponse(user.getEmail(), jwtToken);

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }
}
 