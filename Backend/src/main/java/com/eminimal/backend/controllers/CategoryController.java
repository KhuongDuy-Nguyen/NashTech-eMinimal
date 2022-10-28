package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.CategoryDto;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.services.impl.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    //    Get category
    @GetMapping("/all")
    List<CategoryDto> findAll(){
        return service.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @GetMapping("")
    ResponseEntity<Category> findCategoryById(@RequestParam String id) throws Exception {
        Category category = service.findById(id);
//        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/")
    ResponseEntity<List<Category>> findCategoryByName(@RequestParam String name) throws Exception {
        List<Category> category = service.findByName(name);
//        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(category);
    }

    //    Create new category
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(service.save(category));
    }

    //    Update category
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) throws Exception {
        return new ResponseEntity<>(service.updateCategory(category), HttpStatus.OK);
    }

    //    Remove category
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategoryById(@RequestParam String id) throws Exception {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

}
