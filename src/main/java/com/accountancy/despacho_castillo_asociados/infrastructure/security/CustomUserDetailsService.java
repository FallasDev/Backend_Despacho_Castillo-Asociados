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

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JPAUserRepository userRepository;
    private final JPAClientRepository clientRepository;

    public CustomUserDetailsService(JPAUserRepository userRepository, JPAClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        // Intentar cargar user con role y permisos para evitar proxies perezosos
        Optional<UserEntity> user = userRepository.findByEmailWithRoleAndPermissions(username);

        if (user.isPresent()) {
            UserEntity ue = user.get();

            var authorities = ue.getRole() == null ? java.util.List.<org.springframework.security.core.GrantedAuthority>of()
                    : ue.getRole().getPermissions().stream()
                    .map(pr -> new org.springframework.security.core.authority.SimpleGrantedAuthority(pr.getPermission().getName()))
                    .collect(java.util.stream.Collectors.toSet());

            return new CustomUserDetails(ue.getEmail(), ue.getPassword(), ue.isActive(), authorities);
        }

        List<ClientEntity> client = clientRepository.findByEmailAndIsActive(username, true);

        System.out.println("Cliente encontrado: " + client.size());

        if (!client.isEmpty()) {
            return new CustomClientDetails(client.get(0));
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    public @NonNull UserDetails loadClientByUserName(String username) throws UsernameNotFoundException {
        ClientEntity client = clientRepository.findByEmailAndIsActive(username, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));

        return new CustomClientDetails(client);
    }


}