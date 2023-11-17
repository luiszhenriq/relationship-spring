package br.com.luis.virtualstore.domain.product;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank(message = "Nome do produto é obrigátorio")
        String name,
        @NotNull(message = "Quantidade do produto é obrigátoria")
        Integer quantity,
        @NotNull(message = "Preço do produto é obrigátorio")
        Double price,
        @NotNull(message = "O id de referência a categoria é obrigatorio")
        Long categoryId) {


}
