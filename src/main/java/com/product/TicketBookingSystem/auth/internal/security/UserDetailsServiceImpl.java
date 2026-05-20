package com.product.TicketBookingSystem.auth.internal.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.product.TicketBookingSystem.auth.internal.model.entity.AuthUser;
import com.product.TicketBookingSystem.auth.internal.repository.AuthUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthUserRepository repository;

    public UserDetailsServiceImpl(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUser user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));

        return new UserDetailsImpl(user);
    }
}