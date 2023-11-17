package br.com.luis.virtualstore.domain.product;


import jakarta.validation.constraints.NotNull;

public record UpdateProductDTO(
        @NotNull
        Long id,

        String name,

        Integer quantity,

        Double price) {
}
