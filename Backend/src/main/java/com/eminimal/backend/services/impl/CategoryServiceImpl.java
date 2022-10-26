package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoryServiceImpl{
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository repository;

//  Find category
    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(String id) throws Exception {
        Category category = repository.findByCategoryID(id);
        if(category != null){
            return category;
        }else{
            throw new Exception("Can not find category with id: " + id);
        }
    }

    public List<Category> findByName(String name) throws Exception {
        List<Category> categoryList = repository.findByCategoryNameContaining(name);
        if(categoryList.isEmpty()){
            throw new Exception("Don't have any category with name: " + name);
        }else{
            return categoryList;
        }
    }

//  Create new category
    
    public Category save(Category category){
        return repository.save(category);
    }

//  Delete category
    
    public String deleteById(String uuid) throws Exception {
        findById(uuid);
        repository.deleteById(uuid);
        return "Remove category success";
    }

//  Update category
    
    public Category updateCategory(Category newCategory) throws Exception {
        Category category = findById(newCategory.getCategoryID());
        category.setCategoryName(newCategory.getCategoryName());
        category.setCategoryDesc(newCategory.getCategoryDesc());
        return repository.save(category);
    }

}
