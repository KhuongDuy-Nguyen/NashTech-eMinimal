package com.eminimal.backend.controllers;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

//    Get Information
    @GetMapping("")
    List<Category> findAll(){
        return service.getAllCategory();
    }

//    @GetMapping("/{id}")
//    Optional<Category> findAllByID(@PathVariable UUID id){
//        return service.getCategoryById(id);
//    }

    @GetMapping("/{name}")
    Optional<Category> getAllByName(@PathVariable String name){
        return service.getCategoryByName(name);
    }


//    Create new category
    @PostMapping("")
    Category newCategory(@RequestBody Category newCategory){
        return service.saveCategory(newCategory);
    }

//    Remove category
    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable UUID id){
        service.removeCategoryById(id);
    }

//    Update category
    @PutMapping("/{id}")
    Category replaceCategory(@PathVariable UUID id, @RequestBody Category newCategory){
        return service.updateCategory(id, newCategory);
    }

}
