package br.com.luis.virtualstore.domain.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryDTO(
        @NotNull
        Long id,
        @NotBlank
        String description) {
}
