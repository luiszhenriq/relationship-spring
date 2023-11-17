package br.com.luis.virtualstore.controller;


import br.com.luis.virtualstore.domain.product.ProductDTO;
import br.com.luis.virtualstore.domain.product.ProductData;
import br.com.luis.virtualstore.domain.product.UpdateProductDTO;
import br.com.luis.virtualstore.infra.exception.NotFoundException;
import br.com.luis.virtualstore.models.Product;
import br.com.luis.virtualstore.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {


    @Autowired
    private ProductService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductDTO productDTO)  {
        Product newProduct = service.addProduct(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = service.getAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = service.getById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            throw new NotFoundException("Produto não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProductData> updateProduct (@RequestBody @Valid UpdateProductDTO update) {
        Product newProduct = service.update(update);
        return ResponseEntity.ok(new ProductData(newProduct));
    }

}
