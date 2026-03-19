package com.accountancy.despacho_castillo_asociados.infrastructure.security;

import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.User.JPAUserRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JPAUserRepository userRepository;

    public CustomUserDetailsService(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new CustomUserDetails(user);
    }
}