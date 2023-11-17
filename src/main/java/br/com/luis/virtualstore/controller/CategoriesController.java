package br.com.luis.virtualstore.controller;


import br.com.luis.virtualstore.domain.category.CategoryDTO;
import br.com.luis.virtualstore.domain.category.CategoryData;
import br.com.luis.virtualstore.domain.category.UpdateCategoryDTO;
import br.com.luis.virtualstore.infra.exception.NotFoundException;
import br.com.luis.virtualstore.models.Category;
import br.com.luis.virtualstore.service.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoriesController {

    @Autowired
    private CategoryService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category newCategory = service.addCategory(categoryDTO);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id){
        Optional<Category> optionalCategory = service.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new NotFoundException("Categoria não encontrada com o ID: " + id);
        }

    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> categories = service.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CategoryData> updateCategory(@RequestBody @Valid UpdateCategoryDTO update) {
        Category updatedCategory = service.update(update);
        return ResponseEntity.ok(new CategoryData(updatedCategory));
    }
}
