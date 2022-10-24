package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.CategoryDto;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.services.impl.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
    List<Category> findAll() throws ExecutionException, InterruptedException {
        return service.findAll();
    }

    @GetMapping("/search/id={id}")
    ResponseEntity<CategoryDto> findCategoryById(@PathVariable String id ) throws ExecutionException, InterruptedException {
        Category category = service.findById(id);

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(categoryDto);
    }


    //    Create new category
    @PostMapping("/create")
    String createCategory(@RequestBody Category category) throws ExecutionException, InterruptedException {
//        DTO -> entity
//        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        return service.save(category);

////        Entity -> DTO
//        CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);
//
//        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    //    Update category
    @PutMapping("/update")
    String updateCategory(@RequestBody Category category) throws ExecutionException, InterruptedException {
//        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
//        Category category = service.updateCategory(id, categoryRequest);
//
////        Entity -> DTO
//        CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);
//
//        return ResponseEntity.ok().body(categoryResponse);
        return service.updateCategory(category);

    }

    //    Remove category
    @DeleteMapping("/delete")
    String deleteCategoryById(@RequestParam String id){
        return service.deleteById(id);
//        io.swagger.v3.oas.models.responses.ApiResponse apiResponse = new io.swagger.v3.oas.models.responses.ApiResponse();
//        apiResponse.setDescription("Remove successfully");
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
