package br.com.luis.virtualstore.service;


import br.com.luis.virtualstore.domain.product.ProductDTO;
import br.com.luis.virtualstore.domain.product.UpdateProductDTO;
import br.com.luis.virtualstore.models.Category;
import br.com.luis.virtualstore.models.Product;
import br.com.luis.virtualstore.repositories.CategoryRepository;
import br.com.luis.virtualstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product addProduct(ProductDTO productDTO){

        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.categoryId());

        Category category = categoryOptional.get();

        Product newProduct = new Product(productDTO);
        newProduct.setCategory(category);
        return repository.save(newProduct);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product update(UpdateProductDTO update) {
        Product newProduct = repository.getReferenceById(update.id());
        newProduct.updateProduct(update);
        return newProduct;
    }
}
