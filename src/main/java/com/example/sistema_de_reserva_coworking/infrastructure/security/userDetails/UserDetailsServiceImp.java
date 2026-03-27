package com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails;

import com.example.sistema_de_reserva_coworking.domain.exceptions.NotFound;
import com.example.sistema_de_reserva_coworking.infrastructure.repository.auth.JpaAuthRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final JpaAuthRepository jpaAuthRepository;

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return jpaAuthRepository.findByEmail(email)
                .map(UserDetailsAdapter::new)
                .orElseThrow(() -> new NotFound("Usuaro no encontrado"));
    }
}
