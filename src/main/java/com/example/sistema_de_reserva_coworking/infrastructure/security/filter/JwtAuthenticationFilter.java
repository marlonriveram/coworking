package com.example.sistema_de_reserva_coworking.infrastructure.security.filter;

import com.example.sistema_de_reserva_coworking.domain.model.User;
import com.example.sistema_de_reserva_coworking.domain.repository.AuthRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.security.userDetails.CustomUserPrincipal;
import com.example.sistema_de_reserva_coworking.infrastructure.service.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthRepository authRepository;
    private final Jwt jwt;

    // para que funcione swagger
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/api-docs"); // <--- Añade esto
    }

    @Override
    protected void doFilterInternal(
           @NotNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("Path: " + request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        final Long userId;
        final String role;

        try {
            // Validación existencia del token
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extraccion del token
            token = authHeader.substring(7);

            userEmail = jwt.extractUserEmail(token);

            if (userEmail == null) {
                filterChain.doFilter(request, response);
                return;
            }


            User user = authRepository.findByEmail(userEmail).orElse(null);

            if (user == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!jwt.isTokenValid(token, user)) {
                filterChain.doFilter(request, response);
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                userId = jwt.extractUserId(token);
                role = jwt.extractRole(token);

                if (role == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                CustomUserPrincipal principal = new CustomUserPrincipal(userId, userEmail, role);

                List<SimpleGrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));


                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                principal,
                                null,
                                authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

}
