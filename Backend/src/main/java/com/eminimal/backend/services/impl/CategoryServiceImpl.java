package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements com.eminimal.backend.services.CategoryService {

    @Autowired
    private CategoryRepository repository;

//  Find product
    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }


    @Override
    public Category findById(UUID id) {
        return repository.findByCategoryID(id);
    }

    @Override
    public Optional<Category> findByName(String name){
        return Optional.ofNullable(repository.findByCategoryName(name));
    }

//  Create new product
    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

//  Delete product

    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

//  Update product
    @Override
    public Category updateCategory(UUID id, Category newCategory) {
        Category categoryDB = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid category id:" + id));

        if (Objects.nonNull(newCategory.getCategoryName()) && !"".equalsIgnoreCase(newCategory.getCategoryName())) {
            categoryDB.setCategoryName(newCategory.getCategoryName());
        }

        if (Objects.nonNull(newCategory.getCategoryDesc()) && !"".equalsIgnoreCase(newCategory.getCategoryDesc())) {
            categoryDB.setCategoryDesc(newCategory.getCategoryDesc());
        }
        return repository.save(categoryDB);
    }
}
