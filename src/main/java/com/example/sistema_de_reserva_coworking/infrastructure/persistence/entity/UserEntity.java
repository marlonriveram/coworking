package com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity;

import com.example.sistema_de_reserva_coworking.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole rol;

    @OneToMany(mappedBy = "user")
    private List<BookingEntity> bookings = new ArrayList<>();

}
