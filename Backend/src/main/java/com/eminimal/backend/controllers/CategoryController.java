package com.eminimal.backend.controllers;

import com.eminimal.backend.LoadDatabase;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/category")
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

    @GetMapping("/search/id={id}")
    ResponseEntity<CategoryDto> findCategoryById(@PathVariable UUID id ){
        Category category = service.findById(id);

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(categoryDto);
    }

    @GetMapping("/search/name={name}")
    ResponseEntity<CategoryDto> findCategoryByName(@PathVariable String name){
        Optional<Category> category = service.findByName(name);
        logger.info("find category name: " + name);
        logger.info("Find: " + category);

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        logger.info("Find DTO: " + categoryDto);
        return ResponseEntity.ok().body(categoryDto);
    }

    //    Create new category
    @PostMapping("/create")
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
//        DTO -> entity
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        Category category = service.save(categoryRequest);

//        Entity -> DTO
        CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    //    Update category
    @PutMapping("/update")
    ResponseEntity<CategoryDto> updateCategory(@RequestParam UUID id, @RequestBody CategoryDto categoryDto){
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        Category category = service.updateCategory(id, categoryRequest);

//        Entity -> DTO
        CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

        return ResponseEntity.ok().body(categoryResponse);

    }

    //    Remove category
    @DeleteMapping("/delete")
    ResponseEntity<io.swagger.v3.oas.models.responses.ApiResponse> deleteCategoryById(@RequestParam UUID id){
        service.deleteById(id);
        io.swagger.v3.oas.models.responses.ApiResponse apiResponse = new io.swagger.v3.oas.models.responses.ApiResponse();
        apiResponse.setDescription("Remove successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
