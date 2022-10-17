package com.eminimal.backend.services.impl;

import com.eminimal.backend.dto.CategoryDto;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements com.eminimal.backend.services.CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

//   Covert to DTO
    private CategoryDto covertEntityToDto(Category category){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

//  Covert to Entity
    private Category covertDtoToEntity(CategoryDto categoryDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Category category = new Category();
        category = modelMapper.map(categoryDto, Category.class);
        return category;
    }


//  Find product
    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }


    @Override
    public Optional<Category> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public List<Category> findByName(String name){
        System.out.println(repository.findCategoryByCategoryName(name));
        return repository.findCategoryByCategoryName(name);
    }

//  Create new product
    @Override
    public void save(Category category) {
        repository.save(category);
    }

//  Delete product

    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

//  Update product
    @Override
    public void updateCategory(UUID id, Category newCategory) {
        Category categoryDB = repository.findById(id).get();

        if (Objects.nonNull(newCategory.getCategoryName()) && !"".equalsIgnoreCase(newCategory.getCategoryName())) {
            categoryDB.setCategoryName(newCategory.getCategoryName());
        }

        if (Objects.nonNull(newCategory.getCategoryDesc()) && !"".equalsIgnoreCase(newCategory.getCategoryDesc())) {
            categoryDB.setCategoryDesc(newCategory.getCategoryDesc());
        }
        repository.save(categoryDB);
    }
}
