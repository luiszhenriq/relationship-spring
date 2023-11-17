package br.com.luis.virtualstore.domain.product;

import br.com.luis.virtualstore.models.Category;
import br.com.luis.virtualstore.models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductData(
        @NotNull
        Long id,

        String name,

        Integer quantity,

        Double price,

        Category category) {

    public ProductData(Product product) {
        this(product.getId(), product.getName(), product.getQuantity(), product.getPrice(), product.getCategory());
    }
}
