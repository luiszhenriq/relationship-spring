package br.com.luis.virtualstore.models;


import br.com.luis.virtualstore.domain.product.ProductDTO;
import br.com.luis.virtualstore.domain.product.UpdateProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.quantity = productDTO.quantity();
        this.price = productDTO.price();
    }

    public void updateProduct(UpdateProductDTO update) {
        if (update.name() != null) {
            this.name = update.name();
        }
        if (update.quantity() != null) {
            this.quantity = update.quantity();
        }
        if (update.price() != null) {
            this.price = update.price();
        }
    }
}
