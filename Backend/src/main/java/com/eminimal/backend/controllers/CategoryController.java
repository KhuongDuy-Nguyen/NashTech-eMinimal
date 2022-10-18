package com.eminimal.backend.controllers;

import com.eminimal.backend.dto.CategoryDto;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.services.impl.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    //    Get category
    @GetMapping("/all")
    List<CategoryDto> findAll(){
        return service.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/id={id}")
    ResponseEntity<CategoryDto> findAllByID(@PathVariable UUID id ){
        Category category = service.findById(id).get();

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(categoryDto);
    }

    @GetMapping("/")
    ResponseEntity<CategoryDto> getAllByName(@RequestParam String name){
        List<Category> category = service.findByName(name);
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(categoryDto);
    }

    //    Create new category
    @PostMapping("")
    ResponseEntity<CategoryDto> newCategory(@RequestBody CategoryDto categoryDto){
//        DTO -> entity
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        Category category = service.save(categoryRequest);

//        Entity -> DTO
        CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    //    Update category
    @PutMapping("/{id}")
    ResponseEntity<CategoryDto> replaceCategory(@PathVariable UUID id, @RequestBody CategoryDto categoryDto){
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        Category category = service.updateCategory(id, categoryRequest);

//        Entity -> DTO
        CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

        return ResponseEntity.ok().body(categoryResponse);

    }

    //    Remove category
    @DeleteMapping("/{id}")
    ResponseEntity<io.swagger.v3.oas.models.responses.ApiResponse> deleteCategory(@PathVariable UUID id){
        service.deleteById(id);
        io.swagger.v3.oas.models.responses.ApiResponse apiResponse = new io.swagger.v3.oas.models.responses.ApiResponse();
        apiResponse.setDescription("Remove successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
