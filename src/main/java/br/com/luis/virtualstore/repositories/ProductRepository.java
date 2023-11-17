package br.com.luis.virtualstore.repositories;

import br.com.luis.virtualstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
