package br.com.luis.virtualstore.service;

import br.com.luis.virtualstore.domain.category.CategoryDTO;
import br.com.luis.virtualstore.domain.category.UpdateCategoryDTO;
import br.com.luis.virtualstore.models.Category;
import br.com.luis.virtualstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category addCategory(CategoryDTO category){
        Category newCategory = new Category(category);
        return repository.save(newCategory);
    }

    public Optional<Category> findById(Long id) {
      return  repository.findById(id);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public Category update(UpdateCategoryDTO update) {
        Category newCategory = repository.getReferenceById(update.id());
        newCategory.updateCategory(update);
        return newCategory;
    }
}
