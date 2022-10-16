package com.eminimal.backend.services;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAllCategory(){
        return repository.findAll();
    }

    public Optional<Category> getCategoryById(UUID id){
        return repository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name){
        return repository.findCategoryByCategoryName(name);
    }

    public Category saveCategory(Category category){
        return repository.save(category);
    }

    public void removeCategoryById(UUID id){
        repository.deleteById(id);
    }

    public Category updateCategory(UUID id, Category category) {

        Category categoryDB = repository.findById(id).get();

        if (Objects.nonNull(category.getCategoryName()) && !"".equalsIgnoreCase(category.getCategoryName())) {
            categoryDB.setCategoryName(category.getCategoryName());
        }

        if (Objects.nonNull(category.getCategoryDesc()) && !"".equalsIgnoreCase(category.getCategoryDesc())) {
            categoryDB.setCategoryDesc(category.getCategoryDesc());
        }

        return repository.save(categoryDB);
    }
}
