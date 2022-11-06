package com.eminimal.backend.services.impl;

import com.eminimal.backend.exceptions.NotFoundException;
import com.eminimal.backend.exceptions.ValidationException;
import com.eminimal.backend.exceptions.ResourceFoundException;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.repository.CategoryRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.services.interfaces.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ProductRepository productRepository;


    //  Find category
    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(String id){
        if (id.isBlank())
            throw new ValidationException("ID is requirement");

        Category category = repository.findByCategoryID(id);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }

        return category;
    }

    @Override
    public List<Category> findByName(String name){
        if (name.isBlank())
            throw new ValidationException("Name is requirement");

        List<Category> categoryList = repository.findByCategoryNameContaining(name);
        if (categoryList.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        return categoryList;

    }

    public Category findByCategoryName(String name){
        if (name.isBlank())
            throw new ValidationException("Name is requirement");

        Category category = repository.findByCategoryName(name);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }

        return category;

    }

//  Create new category

    @Override
    public <S extends Category> S save(S entity) throws Exception {
        checkValid(entity);
        if(repository.existsByCategoryName(entity.getCategoryName())){
            throw new Exception("Name have been taken");
        }
        return repository.save(entity);
    }


//  Delete category

    @Override
    public String deleteById(String uuid) throws Exception {
        findById(uuid);
        if(productRepository.findByDetails_Categories_CategoryID(uuid).size() > 0){
            throw new Exception("There are products in category");
        }

        repository.deleteById(uuid);
        return "Remove category success";
    }

//  Update category

    @Override
    public Category updateCategory(Category newCategory) throws Exception {
        Category category = findById(newCategory.getCategoryID());
        checkValid(newCategory);
        category.setCategoryName(newCategory.getCategoryName());
        category.setCategoryDesc(newCategory.getCategoryDesc());
        return save(category);
    }

//    Check valid function
    public void checkValid(Category newCategory) throws NullPointerException{
        if(StringUtils.isEmpty(newCategory.getCategoryName()))
            throw new ValidationException("Category name is require");

        if(StringUtils.isEmpty(newCategory.getCategoryDesc()))
            throw new ValidationException("Category description is require");
//
//        if(newCategory.getCategoryName() == null || newCategory.getCategoryDesc() == null)
//            throw new NullPointerException("Value must not be null");
    }
}
