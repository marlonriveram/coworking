package com.example.sistema_de_reserva_coworking.application.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "email is required")
    @Email(message = "the email is not in the correct format")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 4, max = 10, message = "the password must be between 8 and 10 characters long")
    private String password;

}
