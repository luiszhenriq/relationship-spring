package br.com.luis.virtualstore.models;


import br.com.luis.virtualstore.domain.category.CategoryDTO;
import br.com.luis.virtualstore.domain.category.UpdateCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(CategoryDTO category) {
        this.name = category.name();
        this.description = category.description();
    }

    public void updateCategory(UpdateCategoryDTO update) {
        if (update.description() != null) {
            this.description = update.description();
        }
    }
}
