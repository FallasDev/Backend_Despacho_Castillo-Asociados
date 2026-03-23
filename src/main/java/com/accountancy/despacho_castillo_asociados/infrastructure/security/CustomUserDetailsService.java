package com.accountancy.despacho_castillo_asociados.infrastructure.security;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Client.JPAClientRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.User.JPAUserRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;

import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JPAUserRepository userRepository;
    private final JPAClientRepository clientRepository;

    public CustomUserDetailsService(JPAUserRepository userRepository, JPAClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new CustomUserDetails(user);
    }

    public @NonNull UserDetails loadClientByUserName(String username) throws UsernameNotFoundException {
        ClientEntity client = clientRepository.findByEmailAndIsActive(username, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));

        return new CustomClientDetails(client);
    }


}