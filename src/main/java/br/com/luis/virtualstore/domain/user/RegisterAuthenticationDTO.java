package br.com.luis.virtualstore.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterAuthenticationDTO(
        @NotBlank
        @Email
        String login,
        @NotBlank
        String password,
        UserRole role) {
}
