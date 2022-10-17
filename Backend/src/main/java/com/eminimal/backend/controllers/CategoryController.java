package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.CategoryDto;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;

    //    Get category
    @GetMapping("/all")
    List<Category> findAll(){
        return service.findAll();
    }

    @GetMapping("/id={id}")
    Optional<Category> findAllByID(@PathVariable UUID id ){
        return service.findById(id);
    }

    @GetMapping("/")
    List<Category> getAllByName(@RequestParam String name){
        return service.findByName(name);
    }

    //    Create new category
    @PostMapping("")
    void newCategory(@RequestBody Category newCategory){
        service.save(newCategory);
    }

    //    Remove category
    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable UUID id){
        service.deleteById(id);
    }

    //    Update category
    @PutMapping("/{id}")
    void replaceCategory(@PathVariable UUID id, @RequestBody Category newCategory){
        service.updateCategory(id, newCategory);

    }

}
