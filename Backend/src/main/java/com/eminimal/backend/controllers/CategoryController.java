package com.eminimal.backend.controllers;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/category")
@RestController
public class CategoryController {
    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

//    Get Information
    @GetMapping("")
    Iterable<Category> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Category> findAllByID(@PathVariable UUID id){
        return repository.findById(id);
    }

//    Create new category
    @PostMapping("/")
    Category newCategory(@RequestBody Category newCategory){
        return repository.save(newCategory);
    }

//    Remove category
    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable UUID id){
        repository.deleteById(id);
    }

    @DeleteMapping("")
    void deleteAllCategory(){
        repository.deleteAll();
    }

//    Update category
    @PutMapping("/{id}")
    Category replaceCategory(@PathVariable UUID id, @RequestBody Category newCategory){
        return repository.findById(id)
                .map(category -> {
                   category.setCategoryName(newCategory.getCategoryName());
                   category.setCategoryDesc(newCategory.getCategoryDesc());
                   return repository.save(category);
                })
                .orElseGet(() ->{
                   newCategory.setCategoryID(id);
                    return repository.save(newCategory);
                });
    }

}
