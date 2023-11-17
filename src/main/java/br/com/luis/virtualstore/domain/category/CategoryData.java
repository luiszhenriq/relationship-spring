package br.com.luis.virtualstore.domain.category;

import br.com.luis.virtualstore.models.Category;
import br.com.luis.virtualstore.models.Product;

import java.util.List;

public record CategoryData(

        Long id,

        String name,

        String description,

        List<Product> products) {

    public CategoryData(Category category) {
        this(category.getId(), category.getName(), category.getDescription(), category.getProducts());
    }
}
