package br.com.luis.virtualstore.domain.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "Nome da categoria é obrigátorio")
        String name,

        @NotBlank(message = "Descrição obrigatoria")
        String description) {
}
